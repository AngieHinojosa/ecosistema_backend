package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.Transaccion;
import com.miprimerspring.nuestroecosistema.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaccion")
public class TransaccionRestController {

    //Crear transacción, obtener transacciones, ver transacciones por cuenta.
    @Autowired
    private TransaccionService transaccionService;

    @GetMapping("/lista")
    public ResponseEntity<List<Transaccion>> listar() {
        return ResponseEntity.ok(transaccionService.obtenerTodasLasTransacciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(transaccionService.obtenerTransaccionPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Transaccion> crear(@RequestBody Transaccion obj) {
        return new ResponseEntity<>(transaccionService.crearTransaccion(obj), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Transaccion> actualizar(@PathVariable Long id, @RequestBody Transaccion obj) {
        return ResponseEntity.ok(transaccionService.actualizarTransaccion(id, obj));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        transaccionService.eliminarTransaccion(id);
        return ResponseEntity.noContent().build();
    }
}
