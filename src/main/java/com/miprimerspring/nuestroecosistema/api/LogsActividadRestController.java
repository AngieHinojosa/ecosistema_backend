package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.LogsActividadDTO;
import com.miprimerspring.nuestroecosistema.service.LogsActividadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs-actividad")
@Tag(name = "LogsActividad", description = "Operaciones relacionadas con los logs de actividad de los usuarios")
public class LogsActividadRestController {

    private final LogsActividadService logsActividadService;

    @Autowired
    public LogsActividadRestController(LogsActividadService logsActividadService) {
        this.logsActividadService = logsActividadService;
    }

    @Operation(summary = "Crear un nuevo log de actividad", tags = {"LogsActividad"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log de actividad creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LogsActividadDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<LogsActividadDTO> crearLogActividad(@RequestBody LogsActividadDTO logsActividadDTO) {
        LogsActividadDTO createdLogsActividad = logsActividadService.crearLogActividad(logsActividadDTO);
        return new ResponseEntity<>(createdLogsActividad, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener log de actividad por ID", tags = {"LogsActividad"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Log de actividad encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LogsActividadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Log de actividad no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LogsActividadDTO> obtenerLogActividad(@PathVariable Long id) {
        LogsActividadDTO logsActividadDTO = logsActividadService.obtenerLogActividadPorId(id);
        return new ResponseEntity<>(logsActividadDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener logs de actividad por ID de usuario", tags = {"LogsActividad"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs de actividad obtenidos para el usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron logs para este usuario")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<LogsActividadDTO>> obtenerLogsPorUsuarioId(@PathVariable Integer usuarioId) {
        List<LogsActividadDTO> logs = logsActividadService.obtenerLogsPorUsuarioId(usuarioId);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @Operation(summary = "Obtener logs de actividad por acción", tags = {"LogsActividad"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs de actividad obtenidos por acción", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/accion")
    public ResponseEntity<List<LogsActividadDTO>> obtenerLogsPorAccion(@RequestParam String logAccion) {
        List<LogsActividadDTO> logs = logsActividadService.obtenerLogsPorAccion(logAccion);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todos los logs de actividad", tags = {"LogsActividad"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los logs de actividad obtenidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping
    public ResponseEntity<List<LogsActividadDTO>> obtenerTodosLogs() {
        List<LogsActividadDTO> logs = logsActividadService.obtenerTodosLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un log de actividad", tags = {"LogsActividad"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Log de actividad actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LogsActividadDTO.class))),
            @ApiResponse(responseCode = "404", description = "Log de actividad no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LogsActividadDTO> actualizarLogActividad(@PathVariable Long id, @RequestBody LogsActividadDTO logsActividadDTO) {
        LogsActividadDTO updatedLogsActividad = logsActividadService.actualizarLogActividad(id, logsActividadDTO);
        return new ResponseEntity<>(updatedLogsActividad, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un log de actividad", tags = {"LogsActividad"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Log de actividad eliminado"),
            @ApiResponse(responseCode = "404", description = "Log de actividad no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLogActividad(@PathVariable Long id) {
        logsActividadService.eliminarLogActividad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
