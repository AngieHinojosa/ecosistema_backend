package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Crear un nuevo usuario
    @PostMapping("/nuevo")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    // Obtener usuario por correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorCorreo(@PathVariable("correo") String correo) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorCorreo(correo);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    // Obtener usuarios por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorEstado(@PathVariable("estado") String estado) {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPorEstado(estado);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuarios por rol ID
    @GetMapping("/rol/{rolId}")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorRolId(@PathVariable("rolId") Integer rolId) {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPorRolId(rolId);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuarios por vendedor
    @GetMapping("/vendedor/{vendedor}")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorVendedor(@PathVariable("vendedor") Boolean vendedor) {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPorVendedor(vendedor);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuarios por tipo de documento
    @GetMapping("/tipo-documento/{tipoDocumento}")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorTipoDocumento(@PathVariable("tipoDocumento") String tipoDocumento) {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPorTipoDocumento(tipoDocumento);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuarios por número de documento
    @GetMapping("/numero-documento/{numeroDocumento}")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorNumeroDocumento(@PathVariable("numeroDocumento") String numeroDocumento) {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPorNumeroDocumento(numeroDocumento);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuarios por fecha de nacimiento
    @GetMapping("/fecha-nacimiento/{fechaNacimiento}")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorFechaNacimiento(@PathVariable("fechaNacimiento") String fechaNacimiento) {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPorFechaNacimiento(fechaNacimiento);
        return ResponseEntity.ok(usuarios);
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}