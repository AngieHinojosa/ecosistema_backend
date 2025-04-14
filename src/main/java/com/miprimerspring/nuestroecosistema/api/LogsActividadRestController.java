package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.LogsActividadDTO;
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

    private final LogsActividadService logsActividadService;

    @Autowired
    public LogsActividadRestController(LogsActividadService logsActividadService) {
        this.logsActividadService = logsActividadService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<LogsActividadDTO> crearLogActividad(@RequestBody LogsActividadDTO logsActividadDTO) {
        LogsActividadDTO createdLogsActividad = logsActividadService.crearLogActividad(logsActividadDTO);
        return new ResponseEntity<>(createdLogsActividad, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogsActividadDTO> obtenerLogActividad(@PathVariable Long id) {
        LogsActividadDTO logsActividadDTO = logsActividadService.obtenerLogActividadPorId(id);
        return new ResponseEntity<>(logsActividadDTO, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<LogsActividadDTO>> obtenerLogsPorUsuarioId(@PathVariable Integer usuarioId) {
        List<LogsActividadDTO> logs = logsActividadService.obtenerLogsPorUsuarioId(usuarioId);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @GetMapping("/accion")
    public ResponseEntity<List<LogsActividadDTO>> obtenerLogsPorAccion(@RequestParam String logAccion) {
        List<LogsActividadDTO> logs = logsActividadService.obtenerLogsPorAccion(logAccion);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LogsActividadDTO>> obtenerTodosLogs() {
        List<LogsActividadDTO> logs = logsActividadService.obtenerTodosLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogsActividadDTO> actualizarLogActividad(@PathVariable Long id, @RequestBody LogsActividadDTO logsActividadDTO) {
        LogsActividadDTO updatedLogsActividad = logsActividadService.actualizarLogActividad(id, logsActividadDTO);
        return new ResponseEntity<>(updatedLogsActividad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLogActividad(@PathVariable Long id) {
        logsActividadService.eliminarLogActividad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}