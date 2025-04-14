package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.service.DescuentoAplicadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/descuentos-aplicados")
public class DescuentoAplicadoRestController {

    private final DescuentoAplicadoService descuentoAplicadoService;

    @Autowired
    public DescuentoAplicadoRestController(DescuentoAplicadoService descuentoAplicadoService) {
        this.descuentoAplicadoService = descuentoAplicadoService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<DescuentoAplicadoDTO> crearDescuentoAplicado(@RequestBody DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicadoDTO createdDescuentoAplicado = descuentoAplicadoService.crearDescuentoAplicado(descuentoAplicadoDTO);
        return new ResponseEntity<>(createdDescuentoAplicado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> obtenerDescuentoAplicado(@PathVariable Long id) {
        DescuentoAplicadoDTO descuentoAplicadoDTO = descuentoAplicadoService.obtenerDescuentoAplicadoPorId(id);
        return new ResponseEntity<>(descuentoAplicadoDTO, HttpStatus.OK);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DescuentoAplicadoDTO>> obtenerDescuentosPorPedido(@PathVariable Integer pedidoId) {
        List<DescuentoAplicadoDTO> descuentos = descuentoAplicadoService.obtenerDescuentosPorPedido(pedidoId);
        return new ResponseEntity<>(descuentos, HttpStatus.OK);
    }

    @GetMapping("/descuento/{descuentoId}")
    public ResponseEntity<List<DescuentoAplicadoDTO>> obtenerDescuentosPorDescuento(@PathVariable Integer descuentoId) {
        List<DescuentoAplicadoDTO> descuentos = descuentoAplicadoService.obtenerDescuentosPorDescuento(descuentoId);
        return new ResponseEntity<>(descuentos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DescuentoAplicadoDTO>> obtenerTodosDescuentosAplicados() {
        List<DescuentoAplicadoDTO> descuentos = descuentoAplicadoService.obtenerTodosDescuentosAplicados();
        return new ResponseEntity<>(descuentos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> actualizarDescuentoAplicado(@PathVariable Long id, @RequestBody DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicadoDTO updatedDescuentoAplicado = descuentoAplicadoService.actualizarDescuentoAplicado(id, descuentoAplicadoDTO);
        return new ResponseEntity<>(updatedDescuentoAplicado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDescuentoAplicado(@PathVariable Long id) {
        descuentoAplicadoService.eliminarDescuentoAplicado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}