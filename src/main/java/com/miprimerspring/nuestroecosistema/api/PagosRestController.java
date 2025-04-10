package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.DTO.PagosDTO;
import com.miprimerspring.nuestroecosistema.model.Pagos;
import com.miprimerspring.nuestroecosistema.service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagos")
public class PagosRestController {

    @Autowired
    private PagosService pagosService;

    @GetMapping("/lista")
    public ResponseEntity<List<PagosDTO>> listar() {
        return ResponseEntity.ok(pagosService.obtenerTodosLosPagos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PagosDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(pagosService.obtenerPagoPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PagosDTO> crear(@RequestBody PagosDTO pagoDTO) {
        return new ResponseEntity<>(pagosService.crearPago(pagoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagosService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
