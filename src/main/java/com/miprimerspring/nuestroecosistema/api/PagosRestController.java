package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.PagoDTO;
import com.miprimerspring.nuestroecosistema.service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagos")
public class PagosRestController {

    private final PagosService pagosService;

    @Autowired
    public PagosRestController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PagoDTO> crearPago(@Valid @RequestBody PagoDTO pagoDTO) {
        PagoDTO createdPago = pagosService.crearPago(pagoDTO);
        return ResponseEntity.ok(createdPago);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPagoPorId(@PathVariable Long id) {
        PagoDTO pagoDTO = pagosService.obtenerPagoPorId(id);
        return ResponseEntity.ok(pagoDTO);
    }

    @GetMapping("/porPedido/{pedidoId}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorPedidoId(@PathVariable Integer pedidoId) {
        List<PagoDTO> pagos = pagosService.obtenerPagosPorPedidoId(pedidoId);
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/porCuenta/{cuentaId}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorCuentaId(@PathVariable Integer cuentaId) {
        List<PagoDTO> pagos = pagosService.obtenerPagosPorCuentaId(cuentaId);
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/porMetodo/{metodo}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorMetodo(@PathVariable String metodo) {
        List<PagoDTO> pagos = pagosService.obtenerPagosPorMetodo(metodo);
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PagoDTO>> obtenerTodosPagos() {
        List<PagoDTO> pagos = pagosService.obtenerTodosPagos();
        return ResponseEntity.ok(pagos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizarPago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        PagoDTO updatedPago = pagosService.actualizarPago(id, pagoDTO);
        return ResponseEntity.ok(updatedPago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagosService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}