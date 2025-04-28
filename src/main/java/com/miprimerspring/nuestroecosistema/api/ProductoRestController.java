package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.security.UserDetailsImpl;
import com.miprimerspring.nuestroecosistema.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos en el sistema ecomarket.")
public class ProductoRestController {

    private final ProductoService productoService;

    @Autowired
    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Crear un nuevo producto", description = "Este endpoint permite a los vendedores crear nuevos productos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado. El usuario no está autenticado."),
            @ApiResponse(responseCode = "403", description = "Prohibido. El usuario no tiene el rol de vendedor.")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        // Obtener los detalles del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long usuarioId = userDetails.getUsuarioId();  // Obtenemos el usuarioId desde UserDetailsImpl

            // Verificar si el usuario tiene el rol de vendedor
            if (userDetails.getRoles().contains(ERol.ROLE_VENDEDOR)) {
                // Asociamos el usuarioId al productoDTO antes de enviarlo al servicio
                productoDTO.setUsuarioId(usuarioId);

                // Crear el producto usando el servicio
                ProductoDTO creado = productoService.crearProducto(productoDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(creado);
            } else {
                // Si el usuario no tiene el rol de vendedor, devolver error
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        }

        // Si no hay usuario autenticado, devolver un error
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @Operation(summary = "Obtener todos los productos", description = "Este endpoint permite obtener una lista de todos los productos.")
    @GetMapping("/todos")
    @Tag(name = "Productos")
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        List<ProductoDTO> productos = productoService.obtenerTodosProductos();
        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Obtener un producto por ID", description = "Este endpoint permite obtener un producto especificando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    @Tag(name = "Productos")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable("id") Integer id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar productos por nombre", description = "Este endpoint permite buscar productos por su nombre.")
    @Tag(name = "Productos")
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDTO>> buscarProductosPorNombre(@RequestParam String nombre) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Actualizar un producto", description = "Este endpoint permite actualizar un producto especificado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "403", description = "Prohibido. El usuario no tiene permiso para actualizar el producto."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    })
    @PutMapping("/{id}")
    @Tag(name = "Productos")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable("id") Integer id, @RequestBody ProductoDTO productoDTO) {
        // Obtener los detalles del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long usuarioId = userDetails.getUsuarioId();  // Obtenemos el usuarioId desde UserDetailsImpl

            // Verificar si el usuario es un vendedor o admin y si el producto pertenece al usuario
            ProductoDTO productoExistente = productoService.obtenerProductoPorId(id);
            if (productoExistente == null) {
                return ResponseEntity.notFound().build();
            }

            if (productoExistente.getUsuarioId().equals(usuarioId) || userDetails.getRoles().contains(ERol.ROLE_ADMIN)) {
                // El vendedor solo puede actualizar sus productos, los administradores pueden actualizar cualquiera
                ProductoDTO actualizado = productoService.actualizarProducto(id, productoDTO);
                return ResponseEntity.ok(actualizado);
            } else {
                // El usuario no tiene permisos para actualizar este producto
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        }

        // Si no hay usuario autenticado, devolver un error
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @Operation(summary = "Eliminar un producto", description = "Este endpoint permite eliminar un producto especificado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "403", description = "Prohibido. El usuario no tiene permiso para eliminar el producto."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    })
    @DeleteMapping("/{id}")
    @Tag(name = "Productos")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Integer id) {
        // Obtener los detalles del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long usuarioId = userDetails.getUsuarioId();  // Obtenemos el usuarioId desde UserDetailsImpl

            // Verificar si el usuario es un vendedor o admin y si el producto pertenece al usuario
            ProductoDTO productoExistente = productoService.obtenerProductoPorId(id);
            if (productoExistente == null) {
                return ResponseEntity.notFound().build();
            }

            if (productoExistente.getUsuarioId().equals(usuarioId) || userDetails.getRoles().contains(ERol.ROLE_ADMIN)) {
                // El vendedor solo puede eliminar sus productos, los administradores pueden eliminar cualquiera
                productoService.eliminarProducto(id);
                return ResponseEntity.noContent().build();
            } else {
                // El usuario no tiene permisos para eliminar este producto
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        }

        // Si no hay usuario autenticado, devolver un error
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @Operation(summary = "Obtener mis productos", description = "Este endpoint permite a los vendedores obtener todos sus productos.")
    @GetMapping("/mis-productos")
    @Tag(name = "Productos")
    public ResponseEntity<List<ProductoDTO>> obtenerMisProductos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long usuarioId = userDetails.getUsuarioId();

            List<ProductoDTO> productos = productoService.obtenerProductosPorUsuario(usuarioId);
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Operation(summary = "Obtener productos por categoría", description = "Este endpoint permite obtener productos filtrados por su categoría.")
    @GetMapping("/categoria/{categoriaId}")
    @Tag(name = "Productos")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@PathVariable("categoriaId") Integer categoriaId) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }
}
