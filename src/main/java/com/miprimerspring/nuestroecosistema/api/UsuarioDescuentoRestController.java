package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;
import com.miprimerspring.nuestroecosistema.service.UsuarioDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-descuentos")
public class UsuarioDescuentoRestController {

    private final UsuarioDescuentoService usuarioDescuentoService;

    @Autowired
    public UsuarioDescuentoRestController(UsuarioDescuentoService usuarioDescuentoService) {
        this.usuarioDescuentoService = usuarioDescuentoService;
    }

    // Crear una nueva relación usuario-descuento
    @PostMapping("/nuevo")
    public ResponseEntity<UsuarioDescuentoDTO> crearUsuarioDescuento(@RequestBody UsuarioDescuentoDTO usuarioDescuentoDTO) {
        UsuarioDescuentoDTO usuarioDescuento = usuarioDescuentoService.crearUsuarioDescuento(usuarioDescuentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDescuento);
    }

    // Obtener relación usuario-descuento por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDescuentoDTO> obtenerUsuarioDescuentoPorId(@PathVariable("id") Long id) {
        UsuarioDescuentoDTO usuarioDescuento = usuarioDescuentoService.obtenerUsuarioDescuentoPorId(id);
        return usuarioDescuento != null ? ResponseEntity.ok(usuarioDescuento) : ResponseEntity.notFound().build();
    }

    // Obtener relaciones usuario-descuento por usuario ID
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<UsuarioDescuentoDTO>> obtenerUsuarioDescuentosPorUsuarioId(@PathVariable("usuarioId") Integer usuarioId) {
        List<UsuarioDescuentoDTO> usuarioDescuentos = usuarioDescuentoService.obtenerUsuarioDescuentosPorUsuarioId(usuarioId);
        return ResponseEntity.ok(usuarioDescuentos);
    }

    // Obtener relaciones usuario-descuento por descuento ID
    @GetMapping("/descuento/{descuentoId}")
    public ResponseEntity<List<UsuarioDescuentoDTO>> obtenerUsuarioDescuentosPorDescuentoId(@PathVariable("descuentoId") Long descuentoId) {
        List<UsuarioDescuentoDTO> usuarioDescuentos = usuarioDescuentoService.obtenerUsuarioDescuentosPorDescuentoId(descuentoId);
        return ResponseEntity.ok(usuarioDescuentos);
    }

    // Obtener relación usuario-descuento por usuario ID y descuento ID
    @GetMapping("/usuario/{usuarioId}/descuento/{descuentoId}")
    public ResponseEntity<UsuarioDescuentoDTO> obtenerUsuarioDescuentoPorUsuarioYDescuento(
            @PathVariable("usuarioId") Integer usuarioId,
            @PathVariable("descuentoId") Long descuentoId) {
        UsuarioDescuentoDTO usuarioDescuento = usuarioDescuentoService.obtenerUsuarioDescuentoPorUsuarioYDescuento(usuarioId, descuentoId);
        return usuarioDescuento != null ? ResponseEntity.ok(usuarioDescuento) : ResponseEntity.notFound().build();
    }

    // Eliminar relación usuario-descuento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioDescuento(@PathVariable("id") Long id) {
        usuarioDescuentoService.eliminarUsuarioDescuento(id);
        return ResponseEntity.noContent().build();
    }
}