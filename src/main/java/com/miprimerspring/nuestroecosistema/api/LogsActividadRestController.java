package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.LogsActividad;
import com.miprimerspring.nuestroecosistema.service.LogsActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs-actividad")
public class LogsActividadRestController {

    @Autowired
    private LogsActividadService logsActividadService;

    // Obtener todos los logs de actividad
    @GetMapping("/lista")
    public ResponseEntity<List<LogsActividad>> listar() {
        List<LogsActividad> logs = logsActividadService.obtenerTodosLosLogs();
        return ResponseEntity.ok(logs);
    }

    // Obtener un log de actividad por su ID
    @GetMapping("/{id}")
    public ResponseEntity<LogsActividad> obtener(@PathVariable Long id) {
        LogsActividad log = logsActividadService.obtenerLogPorId(id);
        if (log != null) {
            return ResponseEntity.ok(log);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo log de actividad
    @PostMapping("/nuevo")
    public ResponseEntity<LogsActividad> crear(@RequestBody LogsActividad logsActividad) {
        LogsActividad nuevoLog = logsActividadService.crearLogActividad(logsActividad);
        return new ResponseEntity<>(nuevoLog, HttpStatus.CREATED);
    }

    // Eliminar un log de actividad
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logsActividadService.eliminarLogActividad(id);
        return ResponseEntity.noContent().build();
    }
}