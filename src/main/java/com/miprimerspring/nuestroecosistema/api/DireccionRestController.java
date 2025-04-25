package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.DireccionDTO;
import com.miprimerspring.nuestroecosistema.model.Direccion;
import com.miprimerspring.nuestroecosistema.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direcciones")
public class DireccionRestController {

    private final DireccionService direccionService;

    @Autowired
    public DireccionRestController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @PostMapping("/nueva")
    public ResponseEntity<DireccionDTO> crearDireccion(@RequestBody DireccionDTO direccionDTO) {
        DireccionDTO createdDireccion = direccionService.crearDireccion(direccionDTO);
        return new ResponseEntity<>(createdDireccion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> obtenerDireccion(@PathVariable Integer id) {
        DireccionDTO direccionDTO = direccionService.obtenerDireccionPorId(id);
        return new ResponseEntity<>(direccionDTO, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DireccionDTO>> obtenerDireccionesPorUsuarioId(@PathVariable Integer usuarioId) {
        List<DireccionDTO> direcciones = direccionService.obtenerDireccionesPorUsuarioId(usuarioId);
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> obtenerTodasDirecciones() {
        List<DireccionDTO> direcciones = direccionService.obtenerTodasDirecciones();
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> actualizarDireccion(@PathVariable Integer id, @RequestBody DireccionDTO direccionDTO) {
        DireccionDTO updatedDireccion = direccionService.actualizarDireccion(id, direccionDTO);
        return new ResponseEntity<>(updatedDireccion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Integer id) {
        direccionService.eliminarDireccion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}