package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.service.DescuentoAplicadoService;
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
@RequestMapping("/descuentos-aplicados")
@Tag(name = "DescuentoAplicado", description = "Operaciones relacionadas con los descuentos aplicados")
public class DescuentoAplicadoRestController {

    @Autowired
    private DescuentoAplicadoService descuentoAplicadoService;

    @Operation(summary = "Obtener todos los descuentos aplicados", tags = {"DescuentoAplicado"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Descuentos aplicados obtenidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron descuentos aplicados")
    })
    @GetMapping
    public ResponseEntity<List<DescuentoAplicadoDTO>> obtenerDescuentosAplicados() {
        List<DescuentoAplicadoDTO> descuentos = descuentoAplicadoService.obtenerDescuentosAplicados();
        return ResponseEntity.ok(descuentos);
    }

    @Operation(summary = "Obtener un descuento aplicado por su ID", tags = {"DescuentoAplicado"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Descuento aplicado encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DescuentoAplicadoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Descuento aplicado no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> obtenerDescuentoAplicado(@PathVariable Long id) {
        DescuentoAplicadoDTO descuento = descuentoAplicadoService.obtenerDescuentoAplicadoPorId(id);
        return descuento != null ? ResponseEntity.ok(descuento) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo descuento aplicado", tags = {"DescuentoAplicado"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Descuento aplicado creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DescuentoAplicadoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados")
    })
    @PostMapping
    public ResponseEntity<DescuentoAplicadoDTO> crearDescuentoAplicado(@RequestBody DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicadoDTO descuentoCreado = descuentoAplicadoService.crearDescuentoAplicado(descuentoAplicadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoCreado);
    }

    @Operation(summary = "Actualizar un descuento aplicado", tags = {"DescuentoAplicado"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Descuento aplicado actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DescuentoAplicadoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Descuento aplicado no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> actualizarDescuentoAplicado(@PathVariable Long id, @RequestBody DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicadoDTO descuentoActualizado = descuentoAplicadoService.actualizarDescuentoAplicado(id, descuentoAplicadoDTO);
        return descuentoActualizado != null ? ResponseEntity.ok(descuentoActualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un descuento aplicado", tags = {"DescuentoAplicado"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Descuento aplicado eliminado"),
            @ApiResponse(responseCode = "404", description = "Descuento aplicado no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDescuentoAplicado(@PathVariable Long id) {
        descuentoAplicadoService.eliminarDescuentoAplicado(id);
        return ResponseEntity.noContent().build();
    }
}
