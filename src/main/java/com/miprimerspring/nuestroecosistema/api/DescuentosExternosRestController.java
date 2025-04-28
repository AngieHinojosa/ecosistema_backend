package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.service.DescuentosExternosService;
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
@RequestMapping("/descuentos-externos")
@Tag(name = "DescuentoExterno", description = "Operaciones relacionadas con los descuentos externos")
public class DescuentosExternosRestController {

    @Autowired
    private DescuentosExternosService descuentosExternosService;

    @Operation(summary = "Obtener todos los descuentos externos", tags = {"DescuentoExterno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Descuentos externos obtenidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron descuentos externos")
    })
    @GetMapping
    public ResponseEntity<List<DescuentoExternoDTO>> obtenerDescuentosExternos() {
        List<DescuentoExternoDTO> descuentos = descuentosExternosService.obtenerDescuentosExternos();
        return ResponseEntity.ok(descuentos);
    }

    @Operation(summary = "Obtener un descuento externo por su ID", tags = {"DescuentoExterno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Descuento externo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DescuentoExternoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Descuento externo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DescuentoExternoDTO> obtenerDescuentoExterno(@PathVariable Long id) {
        DescuentoExternoDTO descuento = descuentosExternosService.obtenerDescuentoExternoPorId(id);
        return descuento != null ? ResponseEntity.ok(descuento) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo descuento externo", tags = {"DescuentoExterno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Descuento externo creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DescuentoExternoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados")
    })
    @PostMapping
    public ResponseEntity<DescuentoExternoDTO> crearDescuentoExterno(@RequestBody DescuentoExternoDTO descuentoExternoDTO) {
        DescuentoExternoDTO descuentoCreado = descuentosExternosService.crearDescuentoExterno(descuentoExternoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoCreado);
    }

    @Operation(summary = "Actualizar un descuento externo", tags = {"DescuentoExterno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Descuento externo actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DescuentoExternoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Descuento externo no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DescuentoExternoDTO> actualizarDescuentoExterno(@PathVariable Long id, @RequestBody DescuentoExternoDTO descuentoExternoDTO) {
        DescuentoExternoDTO descuentoActualizado = descuentosExternosService.actualizarDescuentoExterno(id, descuentoExternoDTO);
        return descuentoActualizado != null ? ResponseEntity.ok(descuentoActualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un descuento externo", tags = {"DescuentoExterno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Descuento externo eliminado"),
            @ApiResponse(responseCode = "404", description = "Descuento externo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDescuentoExterno(@PathVariable Long id) {
        descuentosExternosService.eliminarDescuentoExterno(id);
        return ResponseEntity.noContent().build();
    }
}
