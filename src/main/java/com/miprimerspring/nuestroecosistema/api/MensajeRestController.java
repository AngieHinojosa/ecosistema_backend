package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.MensajeDTO;
import com.miprimerspring.nuestroecosistema.service.MensajeService;
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
@RequestMapping("/mensajes")
@Tag(name = "Mensajes", description = "Operaciones relacionadas con los mensajes entre usuarios")
public class MensajeRestController {

    private final MensajeService mensajeService;

    @Autowired
    public MensajeRestController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @Operation(summary = "Crear un nuevo mensaje", tags = {"Mensajes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mensaje creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<MensajeDTO> crearMensaje(@RequestBody MensajeDTO mensajeDTO) {
        MensajeDTO createdMensaje = mensajeService.crearMensaje(mensajeDTO);
        return new ResponseEntity<>(createdMensaje, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener mensaje por ID", tags = {"Mensajes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensaje encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Mensaje no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO> obtenerMensaje(@PathVariable Long id) {
        MensajeDTO mensajeDTO = mensajeService.obtenerMensajePorId(id);
        return new ResponseEntity<>(mensajeDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener mensajes por ID de emisor", tags = {"Mensajes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensajes obtenidos por emisor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron mensajes para este emisor")
    })
    @GetMapping("/emisor/{emisorId}")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajesPorEmisorId(@PathVariable Integer emisorId) {
        List<MensajeDTO> mensajes = mensajeService.obtenerMensajesPorEmisorId(emisorId);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @Operation(summary = "Obtener mensajes por ID de receptor", tags = {"Mensajes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensajes obtenidos por receptor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron mensajes para este receptor")
    })
    @GetMapping("/receptor/{receptorId}")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajesPorReceptorId(@PathVariable Integer receptorId) {
        List<MensajeDTO> mensajes = mensajeService.obtenerMensajesPorReceptorId(receptorId);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @Operation(summary = "Obtener mensajes por estado de lectura", tags = {"Mensajes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensajes obtenidos por estado de lectura", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/leido/{mensajeLeido}")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajesPorLeido(@PathVariable Boolean mensajeLeido) {
        List<MensajeDTO> mensajes = mensajeService.obtenerMensajesPorLeido(mensajeLeido);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todos los mensajes", tags = {"Mensajes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los mensajes obtenidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping
    public ResponseEntity<List<MensajeDTO>> obtenerTodosMensajes() {
        List<MensajeDTO> mensajes = mensajeService.obtenerTodosMensajes();
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un mensaje", tags = {"Mensajes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensaje actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Mensaje no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO> actualizarMensaje(@PathVariable Long id, @RequestBody MensajeDTO mensajeDTO) {
        MensajeDTO updatedMensaje = mensajeService.actualizarMensaje(id, mensajeDTO);
        return new ResponseEntity<>(updatedMensaje, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un mensaje", tags = {"Mensajes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mensaje eliminado"),
            @ApiResponse(responseCode = "404", description = "Mensaje no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
