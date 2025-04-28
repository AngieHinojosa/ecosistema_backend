package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.TransaccionDTO;
import com.miprimerspring.nuestroecosistema.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
@Tag(name = "Transacciones", description = "Operaciones relacionadas con las transacciones financieras.")
public class TransaccionRestController {

    private final TransaccionService transaccionService;

    @Autowired
    public TransaccionRestController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @Operation(summary = "Crear una nueva transacción", description = "Este endpoint permite crear una nueva transacción financiera.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear la transacción")
    })
    @PostMapping
    @Tag(name = "Transacciones")
    public ResponseEntity<?> crearTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
        try {
            TransaccionDTO nuevaTransaccion = transaccionService.crearTransaccion(transaccionDTO);
            return ResponseEntity.ok(nuevaTransaccion);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Error al crear la transacción: " + e.getMessage());
        }
    }

    @Operation(summary = "Obtener transacción por ID", description = "Este endpoint permite obtener una transacción específica por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción encontrada"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    @GetMapping("/{id}")
    @Tag(name = "Transacciones")
    public ResponseEntity<TransaccionDTO> obtenerTransaccionPorId(@PathVariable("id") Long id) {
        TransaccionDTO transaccion = transaccionService.obtenerTransaccionPorId(id);
        return transaccion != null ? ResponseEntity.ok(transaccion) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener transacciones por cuenta ID", description = "Este endpoint permite obtener todas las transacciones asociadas a una cuenta específica.")
    @GetMapping("/cuenta/{cuentaId}")
    @Tag(name = "Transacciones")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorCuentaId(@PathVariable("cuentaId") Integer cuentaId) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorCuentaId(cuentaId);
        return ResponseEntity.ok(transacciones);
    }

    @Operation(summary = "Obtener transacciones por cuenta origen ID", description = "Este endpoint permite obtener todas las transacciones de una cuenta origen específica.")
    @GetMapping("/origen/{origenId}")
    @Tag(name = "Transacciones")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorOrigenId(@PathVariable("origenId") Integer origenId) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorOrigenId(origenId);
        return ResponseEntity.ok(transacciones);
    }

    @Operation(summary = "Obtener transacciones por cuenta destino ID", description = "Este endpoint permite obtener todas las transacciones de una cuenta destino específica.")
    @GetMapping("/destino/{destinoId}")
    @Tag(name = "Transacciones")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorDestinoId(@PathVariable("destinoId") Integer destinoId) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorDestinoId(destinoId);
        return ResponseEntity.ok(transacciones);
    }

    @Operation(summary = "Obtener transacciones por monto", description = "Este endpoint permite obtener todas las transacciones que coincidan con un monto específico.")
    @GetMapping("/monto/{monto}")
    @Tag(name = "Transacciones")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorMonto(@PathVariable("monto") Double monto) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorMonto(monto);
        return ResponseEntity.ok(transacciones);
    }

    @Operation(summary = "Obtener transacciones por fecha", description = "Este endpoint permite obtener todas las transacciones que ocurrieron en una fecha específica.")
    @GetMapping("/fecha/{fecha}")
    @Tag(name = "Transacciones")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorFecha(@PathVariable("fecha") Timestamp fecha) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorFecha(fecha);
        return ResponseEntity.ok(transacciones);
    }

    @Operation(summary = "Eliminar transacción", description = "Este endpoint permite eliminar una transacción específica por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transacción eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    @DeleteMapping("/{id}")
    @Tag(name = "Transacciones")
    public ResponseEntity<Void> eliminarTransaccion(@PathVariable("id") Long id) {
        transaccionService.eliminarTransaccion(id);
        return ResponseEntity.noContent().build();
    }
}
