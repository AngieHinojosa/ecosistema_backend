package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;
import com.miprimerspring.nuestroecosistema.service.PuntosFidelizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntos_fidelizacion")
public class PuntosFidelizacionRestController {

    //Obtener puntos, actualizar puntos.
    @Autowired
    private PuntosFidelizacionService puntosFidelizacionService;

    @GetMapping("/lista")
    public ResponseEntity<List<PuntosFidelizacion>> listar() {
        return ResponseEntity.ok(puntosFidelizacionService.obtenerTodosLosPuntos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntosFidelizacion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(puntosFidelizacionService.obtenerPuntosPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PuntosFidelizacion> crear(@RequestBody PuntosFidelizacion obj) {
        return new ResponseEntity<>(puntosFidelizacionService.crearPuntos(obj), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PuntosFidelizacion> actualizar(@PathVariable Long id, @RequestBody PuntosFidelizacion obj) {
        return ResponseEntity.ok(puntosFidelizacionService.actualizarPuntos(id, obj));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        puntosFidelizacionService.eliminarPuntos(id);
        return ResponseEntity.noContent().build();
    }
}
