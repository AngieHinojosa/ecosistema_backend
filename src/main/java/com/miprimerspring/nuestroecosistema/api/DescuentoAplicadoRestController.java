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

    @Autowired
    private DescuentoAplicadoService descuentoAplicadoService;

    @GetMapping
    public ResponseEntity<List<DescuentoAplicadoDTO>> obtenerDescuentosAplicados() {
        List<DescuentoAplicadoDTO> descuentos = descuentoAplicadoService.obtenerDescuentosAplicados();
        return ResponseEntity.ok(descuentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> obtenerDescuentoAplicado(@PathVariable Long id) {
        DescuentoAplicadoDTO descuento = descuentoAplicadoService.obtenerDescuentoAplicadoPorId(id);
        return descuento != null ? ResponseEntity.ok(descuento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DescuentoAplicadoDTO> crearDescuentoAplicado(@RequestBody DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicadoDTO descuentoCreado = descuentoAplicadoService.crearDescuentoAplicado(descuentoAplicadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> actualizarDescuentoAplicado(@PathVariable Long id, @RequestBody DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicadoDTO descuentoActualizado = descuentoAplicadoService.actualizarDescuentoAplicado(id, descuentoAplicadoDTO);
        return descuentoActualizado != null ? ResponseEntity.ok(descuentoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDescuentoAplicado(@PathVariable Long id) {
        descuentoAplicadoService.eliminarDescuentoAplicado(id);
        return ResponseEntity.noContent().build();
    }
}