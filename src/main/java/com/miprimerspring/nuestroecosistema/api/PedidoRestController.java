package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

    //Crear pedido, obtener pedidos, actualizar estado del pedido.
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> obtenerPedidos(){
        return ResponseEntity.ok(pedidoService.obtenerTodosLosPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.obtenerPedidoPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Pedido> nuevoPedido(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.crearPedido(pedido), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedidoPorId(@PathVariable Long id){
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
