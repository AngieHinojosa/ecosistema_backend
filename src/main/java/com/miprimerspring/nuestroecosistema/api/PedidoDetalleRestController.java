package com.miprimerspring.nuestroecosistema.api;

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

    @Autowired
    private PedidoDetalleService pedidoDetalleService;

    @GetMapping("/lista")
    public ResponseEntity<List<PedidoDetalle>> listar() {
        return ResponseEntity.ok(pedidoDetalleService.obtenerTodosLosDetalles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalle> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoDetalleService.obtenerDetallePorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PedidoDetalle> crear(@RequestBody PedidoDetalle obj) {
        return new ResponseEntity<>(pedidoDetalleService.crearDetalle(obj), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoDetalleService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
