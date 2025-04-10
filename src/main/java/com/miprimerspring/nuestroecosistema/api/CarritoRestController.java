package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.DTO.CarritoDTO;
import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrito")
public class CarritoRestController {

    @Autowired
    private CarritoService carritoService;

    // Obtener todos los carritos
    @GetMapping("/lista")
    public ResponseEntity<List<CarritoDTO>> listar() {
        List<CarritoDTO> carritos = carritoService.obtenerTodosLosCarritosDTO();
        return ResponseEntity.ok(carritos);
    }

    // Obtener un carrito por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CarritoDTO>> obtener(@PathVariable Long id) {
        Optional<CarritoDTO> carritoDTO = carritoService.obtenerCarritoDTOPorId(id);
        return carritoDTO.isPresent() ? ResponseEntity.ok(carritoDTO) : ResponseEntity.notFound().build();
    }

    // Crear un carrito nuevo utilizando CarritoDTO
    @PostMapping("/nuevo")
    public ResponseEntity<Carrito> crearCarrito(@Valid @RequestBody CarritoDTO carritoDTO) {
        Carrito carrito = carritoService.crearCarritoDesdeDTO(carritoDTO);
        return new ResponseEntity<>(carrito, HttpStatus.CREATED);
    }

    // Actualizar un carrito utilizando CarritoDTO
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable Long id, @Valid @RequestBody CarritoDTO carritoDTO) {
        Carrito carrito = carritoService.actualizarCarritoDesdeDTO(id, carritoDTO);
        return ResponseEntity.ok(carrito);
    }

    // Eliminar un carrito
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }
}
