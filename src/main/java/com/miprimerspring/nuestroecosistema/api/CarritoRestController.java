package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
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
@RequestMapping("/carritos")
public class CarritoRestController {

    private final CarritoService carritoService;

    @Autowired
    public CarritoRestController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<CarritoDTO> crearCarrito(@RequestBody CarritoDTO carritoDTO) {
        CarritoDTO createdCarrito = carritoService.crearCarrito(carritoDTO);
        return new ResponseEntity<>(createdCarrito, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable Long id) {
        CarritoDTO carritoDTO = carritoService.obtenerCarritoPorId(id);
        return new ResponseEntity<>(carritoDTO, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CarritoDTO>> obtenerCarritosPorUsuario(@PathVariable Long usuarioId) {
        List<CarritoDTO> carritos = carritoService.obtenerCarritosPorUsuario(usuarioId);
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CarritoDTO>> obtenerTodosCarritos() {
        List<CarritoDTO> carritos = carritoService.obtenerTodosCarritos();
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizarCarrito(@PathVariable Long id, @RequestBody CarritoDTO carritoDTO) {
        CarritoDTO updatedCarrito = carritoService.actualizarCarrito(id, carritoDTO);
        return new ResponseEntity<>(updatedCarrito, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}