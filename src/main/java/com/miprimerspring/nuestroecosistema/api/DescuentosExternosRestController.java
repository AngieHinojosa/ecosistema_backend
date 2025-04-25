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

    @Autowired
    private DescuentosExternosService descuentosExternosService;

    @GetMapping
    public ResponseEntity<List<DescuentoExternoDTO>> obtenerDescuentosExternos() {
        List<DescuentoExternoDTO> descuentos = descuentosExternosService.obtenerDescuentosExternos();
        return ResponseEntity.ok(descuentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescuentoExternoDTO> obtenerDescuentoExterno(@PathVariable Long id) {
        DescuentoExternoDTO descuento = descuentosExternosService.obtenerDescuentoExternoPorId(id);
        return descuento != null ? ResponseEntity.ok(descuento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DescuentoExternoDTO> crearDescuentoExterno(@RequestBody DescuentoExternoDTO descuentoExternoDTO) {
        DescuentoExternoDTO descuentoCreado = descuentosExternosService.crearDescuentoExterno(descuentoExternoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescuentoExternoDTO> actualizarDescuentoExterno(@PathVariable Long id, @RequestBody DescuentoExternoDTO descuentoExternoDTO) {
        DescuentoExternoDTO descuentoActualizado = descuentosExternosService.actualizarDescuentoExterno(id, descuentoExternoDTO);
        return descuentoActualizado != null ? ResponseEntity.ok(descuentoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDescuentoExterno(@PathVariable Long id) {
        descuentosExternosService.eliminarDescuentoExterno(id);
        return ResponseEntity.noContent().build();
    }
}