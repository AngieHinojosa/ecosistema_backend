package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/nuevo")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Usuario> obtenerUsuarioPorCorreo(@PathVariable String correo) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorCorreo(correo);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorEstado(@PathVariable String estado) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorEstado(estado);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorRol(@PathVariable ERol rol) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorRol(rol);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/vendedor/{vendedor}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorVendedor(@PathVariable Boolean vendedor) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorVendedor(vendedor);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/tipo-documento/{tipoDocumento}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorTipoDocumento(@PathVariable String tipoDocumento) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorTipoDocumento(tipoDocumento);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/numero-documento/{numeroDocumento}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorNumeroDocumento(@PathVariable String numeroDocumento) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorNumeroDocumento(numeroDocumento);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/fecha-nacimiento/{fechaNacimiento}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorFechaNacimiento(@PathVariable String fechaNacimiento) {
        try {
            LocalDate fecha = LocalDate.parse(fechaNacimiento);
            List<Usuario> usuarios = usuarioService.obtenerUsuariosPorFechaNacimiento(fecha);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}