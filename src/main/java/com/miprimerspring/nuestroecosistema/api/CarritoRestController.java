package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
import com.miprimerspring.nuestroecosistema.security.UserDetailsImpl;
import com.miprimerspring.nuestroecosistema.service.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/carritos")
@Tag(name = "Carrito", description = "Operaciones relacionadas con los carritos de compras")
public class CarritoRestController {

    private final CarritoService carritoService;

    @Autowired
    public CarritoRestController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // Crear carrito con productos
    @Operation(summary = "Crear un nuevo carrito con productos", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrito creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarritoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en la creación del carrito")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<CarritoDTO> crearCarrito(@RequestBody CarritoDTO carritoDTO) {
        CarritoDTO createdCarrito = carritoService.crearCarrito(carritoDTO);
        return new ResponseEntity<>(createdCarrito, HttpStatus.CREATED);
    }

    // Obtener un carrito (autenticado)
    @Operation(summary = "Obtener un carrito por su ID", description = "Este endpoint permite obtener un carrito específico para el usuario autenticado", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarritoDTO.class))),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido: el carrito no pertenece al usuario"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
            Long usuarioId = userDetails.getUsuarioId();
            CarritoDTO carritoDTO = carritoService.obtenerCarritoPorId(id);
            if (carritoDTO != null && carritoDTO.getUsuarioId().equals(usuarioId)) {
                return ResponseEntity.ok(carritoDTO);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Obtener todos los carritos de un usuario
    @Operation(summary = "Obtener todos los carritos de un usuario", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de carritos obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron carritos para el usuario")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CarritoDTO>> obtenerCarritosPorUsuario(@PathVariable Long usuarioId) {
        List<CarritoDTO> carritos = carritoService.obtenerCarritosPorUsuario(usuarioId);
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    // Obtener todos los carritos (admin)
    @Operation(summary = "Obtener todos los carritos (solo admin)", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de carritos obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido: solo admin puede ver todos los carritos")
    })
    @GetMapping
    public ResponseEntity<List<CarritoDTO>> obtenerTodosCarritos() {
        List<CarritoDTO> carritos = carritoService.obtenerTodosCarritos();
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    // Actualizar carrito completo (lista de productos incluida)
    @Operation(summary = "Actualizar un carrito con una nueva lista de productos", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarritoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el carrito")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizarCarrito(@PathVariable Long id, @RequestBody CarritoDTO carritoDTO) {
        CarritoDTO updatedCarrito = carritoService.actualizarCarrito(id, carritoDTO);
        return new ResponseEntity<>(updatedCarrito, HttpStatus.OK);
    }

    // Eliminar carrito completo
    @Operation(summary = "Eliminar un carrito", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carrito eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Agregar producto a carrito (con cantidad)
    @Operation(summary = "Agregar un producto a un carrito", description = "Este endpoint agrega un producto a un carrito, especificando la cantidad", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto agregado al carrito exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarritoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Carrito o producto no encontrado")
    })
    @PostMapping("/{carritoId}/agregar-producto")
    public ResponseEntity<CarritoDTO> agregarProductoAlCarrito(
            @PathVariable Long carritoId,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {

        CarritoDTO carritoDTO = carritoService.agregarProductoAlCarrito(carritoId, productoId, cantidad);
        return carritoDTO != null
                ? ResponseEntity.ok(carritoDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Calcular total
    @Operation(summary = "Calcular el total del carrito", description = "Este endpoint calcula el total del carrito basándose en los productos añadidos", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total calculado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @GetMapping("/{carritoId}/total")
    public ResponseEntity<Double> obtenerTotalCarrito(@PathVariable Long carritoId) {
        Double total = carritoService.calcularTotalCarrito(carritoId);
        return total != null
                ? ResponseEntity.ok(total)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar producto específico de un carrito
    @Operation(summary = "Eliminar un producto de un carrito", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado del carrito exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarritoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Carrito o producto no encontrado")
    })
    @DeleteMapping("/{carritoId}/eliminar-producto/{productoId}")
    public ResponseEntity<CarritoDTO> eliminarProductoDelCarrito(
            @PathVariable Long carritoId,
            @PathVariable Long productoId) {

        CarritoDTO carritoDTO = carritoService.eliminarProductoDelCarrito(carritoId, productoId);
        return carritoDTO != null
                ? ResponseEntity.ok(carritoDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Pago del carrito
    @Operation(summary = "Pagar el carrito", description = "Este endpoint permite realizar el pago de un carrito utilizando un método de pago", tags = {"Carrito"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago realizado con éxito"),
            @ApiResponse(responseCode = "400", description = "No se pudo procesar el pago")
    })
    @PostMapping("/{carritoId}/pagar")
    public ResponseEntity<String> pagarCarrito(@PathVariable Long carritoId, @RequestParam String metodoPago) {
        boolean pagoExitoso = carritoService.pagarCarrito(carritoId, metodoPago);
        return pagoExitoso
                ? ResponseEntity.ok("Pago realizado con éxito")
                : ResponseEntity.badRequest().body("No se pudo procesar el pago");
    }
}