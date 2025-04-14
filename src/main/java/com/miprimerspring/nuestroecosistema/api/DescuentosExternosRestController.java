package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.service.DescuentosExternosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descuentos-externos")
public class DescuentosExternosRestController {

    private final DescuentosExternosService descuentosExternosService;

    @Autowired
    public DescuentosExternosRestController(DescuentosExternosService descuentosExternosService) {
        this.descuentosExternosService = descuentosExternosService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<DescuentoExternoDTO> crearDescuentoExterno(@RequestBody DescuentoExternoDTO descuentoExternoDTO) {
        DescuentoExternoDTO createdDescuentoExterno = descuentosExternosService.crearDescuentoExterno(descuentoExternoDTO);
        return new ResponseEntity<>(createdDescuentoExterno, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescuentoExternoDTO> obtenerDescuentoExterno(@PathVariable Integer id) {
        DescuentoExternoDTO descuentoExternoDTO = descuentosExternosService.obtenerDescuentoExternoPorId(id);
        return new ResponseEntity<>(descuentoExternoDTO, HttpStatus.OK);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<DescuentoExternoDTO>> obtenerDescuentosActivos(@RequestParam Boolean descuentoActivo) {
        List<DescuentoExternoDTO> descuentos = descuentosExternosService.obtenerDescuentosActivos(descuentoActivo);
        return new ResponseEntity<>(descuentos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DescuentoExternoDTO>> obtenerTodosDescuentosExternos() {
        List<DescuentoExternoDTO> descuentos = descuentosExternosService.obtenerTodosDescuentosExternos();
        return new ResponseEntity<>(descuentos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescuentoExternoDTO> actualizarDescuentoExterno(@PathVariable Integer id, @RequestBody DescuentoExternoDTO descuentoExternoDTO) {
        DescuentoExternoDTO updatedDescuentoExterno = descuentosExternosService.actualizarDescuentoExterno(id, descuentoExternoDTO);
        return new ResponseEntity<>(updatedDescuentoExterno, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDescuentoExterno(@PathVariable Integer id) {
        descuentosExternosService.eliminarDescuentoExterno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}