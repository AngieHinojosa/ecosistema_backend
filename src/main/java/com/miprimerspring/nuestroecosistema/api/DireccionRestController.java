package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.Direccion;
import com.miprimerspring.nuestroecosistema.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class DireccionRestController {

    //Gestionar las direcciones de envío y facturación de los usuarios
    //Crear dirección, obtener direcciones, actualizar dirección, eliminar dirección.

    @Autowired
    private DireccionService direccionService;

    @GetMapping("/lista")
    public ResponseEntity<List<Direccion>> listar() {
        return ResponseEntity.ok(direccionService.obtenerTodasLasDirecciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(direccionService.obtenerDireccionPorId(id));
    }

    @PostMapping("/nueva")
    public ResponseEntity<Direccion> crear(@RequestBody Direccion obj) {
        return new ResponseEntity<>(direccionService.crearDireccion(obj), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Direccion> actualizar(@PathVariable Long id, @RequestBody Direccion obj) {
        return ResponseEntity.ok(direccionService.actualizarDireccion(id, obj));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
}

