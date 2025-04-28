package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.DireccionDTO;
import com.miprimerspring.nuestroecosistema.service.DireccionService;
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
@RequestMapping("/direcciones")
@Tag(name = "Direccion", description = "Operaciones relacionadas con las direcciones de los usuarios")
public class DireccionRestController {

    private final DireccionService direccionService;

    @Autowired
    public DireccionRestController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @Operation(summary = "Crear una nueva dirección", tags = {"Direccion"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dirección creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DireccionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados")
    })
    @PostMapping("/nueva")
    public ResponseEntity<DireccionDTO> crearDireccion(@RequestBody DireccionDTO direccionDTO) {
        DireccionDTO createdDireccion = direccionService.crearDireccion(direccionDTO);
        return new ResponseEntity<>(createdDireccion, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener dirección por ID", tags = {"Direccion"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DireccionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> obtenerDireccion(@PathVariable Integer id) {
        DireccionDTO direccionDTO = direccionService.obtenerDireccionPorId(id);
        return new ResponseEntity<>(direccionDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las direcciones de un usuario", tags = {"Direccion"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Direcciones obtenidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron direcciones")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DireccionDTO>> obtenerDireccionesPorUsuarioId(@PathVariable Integer usuarioId) {
        List<DireccionDTO> direcciones = direccionService.obtenerDireccionesPorUsuarioId(usuarioId);
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las direcciones", tags = {"Direccion"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Direcciones obtenidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping
    public ResponseEntity<List<DireccionDTO>> obtenerTodasDirecciones() {
        List<DireccionDTO> direcciones = direccionService.obtenerTodasDirecciones();
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar una dirección", tags = {"Direccion"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DireccionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> actualizarDireccion(@PathVariable Integer id, @RequestBody DireccionDTO direccionDTO) {
        DireccionDTO updatedDireccion = direccionService.actualizarDireccion(id, direccionDTO);
        return new ResponseEntity<>(updatedDireccion, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una dirección", tags = {"Direccion"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dirección eliminada"),
            @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Integer id) {
        direccionService.eliminarDireccion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
