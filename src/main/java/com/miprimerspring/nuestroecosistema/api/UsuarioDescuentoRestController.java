package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.service.UsuarioDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-descuentos")
public class UsuarioDescuentoRestController {

    @Autowired
    private UsuarioDescuentoService usuarioDescuentoService;

    @GetMapping
    public ResponseEntity<List<UsuarioDescuentoDTO>> obtenerUsuarioDescuentos() {
        List<UsuarioDescuentoDTO> descuentos = usuarioDescuentoService.obtenerUsuarioDescuentos();
        return ResponseEntity.ok(descuentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDescuentoDTO> obtenerUsuarioDescuento(@PathVariable Long id) {
        UsuarioDescuentoDTO descuento = usuarioDescuentoService.obtenerUsuarioDescuentoPorId(id);
        return descuento != null ? ResponseEntity.ok(descuento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioDescuentoDTO> crearUsuarioDescuento(@RequestBody UsuarioDescuentoDTO usuarioDescuentoDTO) {
        UsuarioDescuentoDTO descuentoCreado = usuarioDescuentoService.crearUsuarioDescuento(usuarioDescuentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioDescuento(@PathVariable Long id) {
        usuarioDescuentoService.eliminarUsuarioDescuento(id);
        return ResponseEntity.noContent().build();
    }
}