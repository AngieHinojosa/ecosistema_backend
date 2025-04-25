package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.security.UserDetailsImpl;
import com.miprimerspring.nuestroecosistema.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoRestController {

    private final ProductoService productoService;

    @Autowired
    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Crear un nuevo producto
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

    @GetMapping("/todos")
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        List<ProductoDTO> productos = productoService.obtenerTodosProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable("id") Integer id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDTO>> buscarProductosPorNombre(@RequestParam String nombre) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    // Actualizar un producto
    @PutMapping("/{id}")
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

    // Eliminar un producto
    @DeleteMapping("/{id}")
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

    @GetMapping("/mis-productos")
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

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@PathVariable("categoriaId") Integer categoriaId) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }
}