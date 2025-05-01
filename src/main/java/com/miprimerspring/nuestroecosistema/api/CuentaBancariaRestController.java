package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.service.CuentaBancariaService;
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
@RequestMapping("/cuentas")
@Tag(name = "CuentaBancaria", description = "Operaciones relacionadas con las cuentas bancarias")
public class CuentaBancariaRestController {

    private final CuentaBancariaService cuentaBancariaService;

    @Autowired
    public CuentaBancariaRestController(CuentaBancariaService cuentaBancariaService) {
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @Operation(summary = "Obtener una cuenta bancaria por su ID", tags = {"CuentaBancaria"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta bancaria encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaBancariaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cuenta bancaria no encontrada")
    })
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @GetMapping("/{id}")
    public ResponseEntity<CuentaBancariaDTO> obtenerCuentaBancaria(@PathVariable Long id) {
        CuentaBancariaDTO cuentaBancariaDTO = cuentaBancariaService.obtenerCuentaBancariaPorId(id);
        return cuentaBancariaDTO != null
                ? ResponseEntity.ok(cuentaBancariaDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Obtener todas las cuentas bancarias de un usuario", tags = {"CuentaBancaria"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuentas bancarias obtenidas exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron cuentas bancarias para el usuario")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CuentaBancariaDTO>> obtenerCuentasPorUsuario(@PathVariable Integer usuarioId) {
        List<CuentaBancariaDTO> cuentas = cuentaBancariaService.obtenerCuentasPorUsuario(Long.valueOf(usuarioId));
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las cuentas bancarias", tags = {"CuentaBancaria"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cuentas bancarias obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron cuentas bancarias")
    })
    @GetMapping
    public ResponseEntity<List<CuentaBancariaDTO>> obtenerTodasCuentas() {
        List<CuentaBancariaDTO> cuentas = cuentaBancariaService.obtenerTodasCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar una cuenta bancaria", tags = {"CuentaBancaria"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta bancaria actualizada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaBancariaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error al actualizar la cuenta bancaria")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CuentaBancariaDTO> actualizarCuentaBancaria(@PathVariable Long id, @RequestBody CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancariaDTO updatedCuentaBancaria = cuentaBancariaService.actualizarCuentaBancaria(id, cuentaBancariaDTO);
        return new ResponseEntity<>(updatedCuentaBancaria, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una cuenta bancaria", tags = {"CuentaBancaria"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cuenta bancaria eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta bancaria no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaBancaria(@PathVariable Long id) {
        cuentaBancariaService.eliminarCuentaBancaria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
