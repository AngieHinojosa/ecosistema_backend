package com.miprimerspring.nuestroecosistema.api;

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

    @GetMapping("/lista")
    public ResponseEntity<List<DescuentosExternos>> listar() {
        return ResponseEntity.ok(descuentosExternosService.obtenerTodosLosDescuentosExternos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescuentosExternos> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(descuentosExternosService.obtenerDescuentoExternoPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<DescuentosExternos> crear(@RequestBody DescuentosExternos obj) {
        return new ResponseEntity<>(descuentosExternosService.crearDescuentoExterno(obj), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DescuentosExternos> actualizar(@PathVariable Long id, @RequestBody DescuentosExternos obj) {
        return ResponseEntity.ok(descuentosExternosService.actualizarDescuentoExterno(id, obj));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        descuentosExternosService.eliminarDescuentoExterno(id);
        return ResponseEntity.noContent().build();
    }
}
