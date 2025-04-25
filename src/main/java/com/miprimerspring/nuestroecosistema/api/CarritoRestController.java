package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.security.UserDetailsImpl;
import com.miprimerspring.nuestroecosistema.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carritos")
public class CarritoRestController {

    private final CarritoService carritoService;

    @Autowired
    public CarritoRestController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // ✅ Crear carrito con productos
    @PostMapping("/nuevo")
    public ResponseEntity<CarritoDTO> crearCarrito(@RequestBody CarritoDTO carritoDTO) {
        CarritoDTO createdCarrito = carritoService.crearCarrito(carritoDTO);
        return new ResponseEntity<>(createdCarrito, HttpStatus.CREATED);
    }

    // ✅ Obtener un carrito (autenticado)
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

    // ✅ Obtener todos los carritos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CarritoDTO>> obtenerCarritosPorUsuario(@PathVariable Long usuarioId) {
        List<CarritoDTO> carritos = carritoService.obtenerCarritosPorUsuario(usuarioId);
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    // ✅ Obtener todos los carritos (admin)
    @GetMapping
    public ResponseEntity<List<CarritoDTO>> obtenerTodosCarritos() {
        List<CarritoDTO> carritos = carritoService.obtenerTodosCarritos();
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    // ✅ Actualizar carrito completo (lista de productos incluida)
    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizarCarrito(@PathVariable Long id, @RequestBody CarritoDTO carritoDTO) {
        CarritoDTO updatedCarrito = carritoService.actualizarCarrito(id, carritoDTO);
        return new ResponseEntity<>(updatedCarrito, HttpStatus.OK);
    }

    // ✅ Eliminar carrito completo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Agregar producto a carrito (con cantidad)
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

    // ✅ Calcular total
    @GetMapping("/{carritoId}/total")
    public ResponseEntity<Double> obtenerTotalCarrito(@PathVariable Long carritoId) {
        Double total = carritoService.calcularTotalCarrito(carritoId);
        return total != null
                ? ResponseEntity.ok(total)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // ✅ Eliminar producto específico de un carrito
    @DeleteMapping("/{carritoId}/eliminar-producto/{productoId}")
    public ResponseEntity<CarritoDTO> eliminarProductoDelCarrito(
            @PathVariable Long carritoId,
            @PathVariable Long productoId) {

        CarritoDTO carritoDTO = carritoService.eliminarProductoDelCarrito(carritoId, productoId);
        return carritoDTO != null
                ? ResponseEntity.ok(carritoDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // ✅ Pago del carrito
    @PostMapping("/{carritoId}/pagar")
    public ResponseEntity<String> pagarCarrito(@PathVariable Long carritoId, @RequestParam String metodoPago) {
        boolean pagoExitoso = carritoService.pagarCarrito(carritoId, metodoPago);
        return pagoExitoso
                ? ResponseEntity.ok("Pago realizado con éxito")
                : ResponseEntity.badRequest().body("No se pudo procesar el pago");
    }
}