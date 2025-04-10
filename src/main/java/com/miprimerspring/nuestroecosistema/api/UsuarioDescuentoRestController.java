package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;
import com.miprimerspring.nuestroecosistema.service.UsuarioDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-descuento")
public class UsuarioDescuentoRestController {

    @Autowired
    private UsuarioDescuentoService usuarioDescuentoService;

    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioDescuento>> listar() {
        return ResponseEntity.ok(usuarioDescuentoService.obtenerTodosLosUsuarioDescuentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDescuento> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioDescuentoService.obtenerUsuarioDescuentoPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<UsuarioDescuento> crear(@RequestBody UsuarioDescuento obj) {
        return new ResponseEntity<>(usuarioDescuentoService.crearUsuarioDescuento(obj), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioDescuento> actualizar(@PathVariable Long id, @RequestBody UsuarioDescuento obj) {
        return ResponseEntity.ok(usuarioDescuentoService.actualizarUsuarioDescuento(id, obj));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioDescuentoService.eliminarUsuarioDescuento(id);
        return ResponseEntity.noContent().build();
    }
}
