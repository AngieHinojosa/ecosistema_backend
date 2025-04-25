package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.PedidoDetalleDTO;
import com.miprimerspring.nuestroecosistema.model.PedidoDetalle;
import com.miprimerspring.nuestroecosistema.service.PedidoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-detalle")
public class PedidoDetalleRestController {

    private final PedidoDetalleService pedidoDetalleService;

    @Autowired
    public PedidoDetalleRestController(PedidoDetalleService pedidoDetalleService) {
        this.pedidoDetalleService = pedidoDetalleService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PedidoDetalleDTO> crearPedidoDetalle(@RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        PedidoDetalleDTO createdPedidoDetalle = pedidoDetalleService.crearPedidoDetalle(pedidoDetalleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPedidoDetalle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> obtenerPedidoDetallePorId(@PathVariable Long id) {
        PedidoDetalleDTO pedidoDetalleDTO = pedidoDetalleService.obtenerPedidoDetallePorId(id);
        if (pedidoDetalleDTO != null) {
            return ResponseEntity.ok(pedidoDetalleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/porPedido/{pedidoId}")
    public ResponseEntity<List<PedidoDetalleDTO>> obtenerDetallesPorPedido(@PathVariable Integer pedidoId) {
        List<PedidoDetalleDTO> detalles = pedidoDetalleService.obtenerDetallesPorPedido(pedidoId);
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/porProducto/{productoId}")
    public ResponseEntity<List<PedidoDetalleDTO>> obtenerDetallesPorProducto(@PathVariable Integer productoId) {
        List<PedidoDetalleDTO> detalles = pedidoDetalleService.obtenerDetallesPorProducto(productoId);
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoDetalleDTO>> obtenerTodosDetalles() {
        List<PedidoDetalleDTO> detalles = pedidoDetalleService.obtenerTodosDetalles();
        return ResponseEntity.ok(detalles);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PedidoDetalleDTO> actualizarPedidoDetalle(@PathVariable Long id, @RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        PedidoDetalleDTO updatedPedidoDetalle = pedidoDetalleService.actualizarPedidoDetalle(id, pedidoDetalleDTO);
        if (updatedPedidoDetalle != null) {
            return ResponseEntity.ok(updatedPedidoDetalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedidoDetalle(@PathVariable Long id) {
        pedidoDetalleService.eliminarPedidoDetalle(id);
        return ResponseEntity.noContent().build();
    }
}