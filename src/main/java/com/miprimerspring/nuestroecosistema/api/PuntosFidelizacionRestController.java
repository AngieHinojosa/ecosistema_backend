package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.PuntosFidelizacionDTO;
import com.miprimerspring.nuestroecosistema.service.PuntosFidelizacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntos_fidelizacion")
@Tag(name = "Puntos Fidelización", description = "Operaciones relacionadas con los puntos de fidelización de usuarios.")
public class PuntosFidelizacionRestController {

    private final PuntosFidelizacionService puntosFidelizacionService;

    @Autowired
    public PuntosFidelizacionRestController(PuntosFidelizacionService puntosFidelizacionService) {
        this.puntosFidelizacionService = puntosFidelizacionService;
    }

    @Operation(summary = "Obtener todos los puntos de fidelización", description = "Este endpoint devuelve todos los puntos de fidelización disponibles en el sistema.")
    @GetMapping("/todos")
    @Tag(name = "Puntos Fidelización")
    public ResponseEntity<List<PuntosFidelizacionDTO>> obtenerTodosPuntos() {
        List<PuntosFidelizacionDTO> puntos = puntosFidelizacionService.obtenerTodosPuntosFidelizacion();
        return ResponseEntity.ok(puntos);
    }

    @Operation(summary = "Obtener un punto de fidelización por ID", description = "Este endpoint permite obtener un punto de fidelización por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Punto de fidelización encontrado"),
            @ApiResponse(responseCode = "404", description = "Punto de fidelización no encontrado")
    })
    @GetMapping("/{id}")
    @Tag(name = "Puntos Fidelización")
    public ResponseEntity<PuntosFidelizacionDTO> obtenerPuntoPorId(@PathVariable("id") Long id) {
        PuntosFidelizacionDTO punto = puntosFidelizacionService.obtenerPuntoFidelizacionPorId(id);
        return punto != null ? ResponseEntity.ok(punto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener puntos de fidelización por usuario", description = "Este endpoint permite obtener los puntos de fidelización de un usuario específico.")
    @GetMapping("/usuario/{usuarioId}")
    @Tag(name = "Puntos Fidelización")
    public ResponseEntity<List<PuntosFidelizacionDTO>> obtenerPuntosPorUsuario(@PathVariable("usuarioId") Integer usuarioId) {
        List<PuntosFidelizacionDTO> puntos = puntosFidelizacionService.obtenerPuntosPorUsuario(usuarioId);
        return ResponseEntity.ok(puntos);
    }

    @Operation(summary = "Crear un nuevo punto de fidelización", description = "Este endpoint permite crear un nuevo punto de fidelización para un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Punto de fidelización creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Los datos proporcionados no son válidos.")
    })
    @PostMapping("/nuevo")
    @Tag(name = "Puntos Fidelización")
    public ResponseEntity<PuntosFidelizacionDTO> crearPuntoFidelizacion(@RequestBody PuntosFidelizacionDTO puntosFidelizacionDTO) {
        PuntosFidelizacionDTO creado = puntosFidelizacionService.crearPuntoFidelizacion(puntosFidelizacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar un punto de fidelización", description = "Este endpoint permite actualizar un punto de fidelización especificado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Punto de fidelización actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Punto de fidelización no encontrado")
    })
    @PutMapping("/{id}")
    @Tag(name = "Puntos Fidelización")
    public ResponseEntity<PuntosFidelizacionDTO> actualizarPuntoFidelizacion(@PathVariable("id") Long id, @RequestBody PuntosFidelizacionDTO puntosFidelizacionDTO) {
        PuntosFidelizacionDTO actualizado = puntosFidelizacionService.actualizarPuntoFidelizacion(id, puntosFidelizacionDTO);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un punto de fidelización", description = "Este endpoint permite eliminar un punto de fidelización especificado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Punto de fidelización eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Punto de fidelización no encontrado")
    })
    @DeleteMapping("/{id}")
    @Tag(name = "Puntos Fidelización")
    public ResponseEntity<Void> eliminarPuntoFidelizacion(@PathVariable("id") Long id) {
        puntosFidelizacionService.eliminarPuntoFidelizacion(id);
        return ResponseEntity.noContent().build();
    }
}
