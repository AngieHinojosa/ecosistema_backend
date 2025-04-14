package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.TransaccionDTO;
import com.miprimerspring.nuestroecosistema.model.Transaccion;
import com.miprimerspring.nuestroecosistema.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionRestController {

    private final TransaccionService transaccionService;

    @Autowired
    public TransaccionRestController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    // Crear una nueva transacción
    @PostMapping("/nueva")
    public ResponseEntity<TransaccionDTO> crearTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
        TransaccionDTO transaccion = transaccionService.crearTransaccion(transaccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaccion);
    }

    // Obtener transacción por ID
    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDTO> obtenerTransaccionPorId(@PathVariable("id") Long id) {
        TransaccionDTO transaccion = transaccionService.obtenerTransaccionPorId(id);
        return transaccion != null ? ResponseEntity.ok(transaccion) : ResponseEntity.notFound().build();
    }

    // Obtener transacciones por cuenta ID
    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorCuentaId(@PathVariable("cuentaId") Integer cuentaId) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorCuentaId(cuentaId);
        return ResponseEntity.ok(transacciones);
    }

    // Obtener transacciones por cuenta origen ID
    @GetMapping("/origen/{origenId}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorOrigenId(@PathVariable("origenId") Integer origenId) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorOrigenId(origenId);
        return ResponseEntity.ok(transacciones);
    }

    // Obtener transacciones por cuenta destino ID
    @GetMapping("/destino/{destinoId}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorDestinoId(@PathVariable("destinoId") Integer destinoId) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorDestinoId(destinoId);
        return ResponseEntity.ok(transacciones);
    }

    // Obtener transacciones por monto
    @GetMapping("/monto/{monto}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorMonto(@PathVariable("monto") Double monto) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorMonto(monto);
        return ResponseEntity.ok(transacciones);
    }

    // Obtener transacciones por fecha
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorFecha(@PathVariable("fecha") Timestamp fecha) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorFecha(fecha);
        return ResponseEntity.ok(transacciones);
    }

    // Eliminar transacción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransaccion(@PathVariable("id") Long id) {
        transaccionService.eliminarTransaccion(id);
        return ResponseEntity.noContent().build();
    }
}