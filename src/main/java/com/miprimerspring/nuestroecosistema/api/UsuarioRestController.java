package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.DTO.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuariosDTO = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuariosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.obtenerUsuarioPorId(id);
        return usuarioDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/nuevo")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody Usuario usuario) {
        UsuarioDTO usuarioDTO = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        UsuarioDTO usuarioDTO = usuarioService.actualizarUsuario(id, usuario);
        return usuarioDTO != null ? ResponseEntity.ok(usuarioDTO) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}