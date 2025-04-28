package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios del sistema.")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Este endpoint permite registrar un nuevo usuario en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la creación del usuario")
    })
    @PostMapping("/registro")
    @Tag(name = "Usuarios")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuarioCreado = usuarioService.registrarDesdeDTO(dto);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener usuario por ID", description = "Este endpoint permite obtener un usuario específico mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    @Tag(name = "Usuarios")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener usuario por correo", description = "Este endpoint permite obtener un usuario específico mediante su correo electrónico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/correo/{correo}")
    @Tag(name = "Usuarios")
    public ResponseEntity<Usuario> obtenerUsuarioPorCorreo(@PathVariable String correo) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorCorreo(correo);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener usuarios por estado", description = "Este endpoint permite obtener una lista de usuarios según su estado (activo, inactivo, bloqueado).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
            @ApiResponse(responseCode = "400", description = "Estado inválido")
    })
    @GetMapping("/estado/{estado}")
    @Tag(name = "Usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorEstado(@PathVariable String estado) {
        if (!estado.matches("ACTIVO|INACTIVO|BLOQUEADO")) {
            return ResponseEntity.badRequest().build();
        }
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorEstado(estado);
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por rol", description = "Este endpoint permite obtener usuarios según su rol (ADMIN, USER, etc.).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios con el rol especificado")
    })
    @GetMapping("/rol/{rol}")
    @Tag(name = "Usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorRol(@PathVariable ERol rol) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorRol(rol);
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por tipo de documento", description = "Este endpoint permite obtener una lista de usuarios según el tipo de documento (RUT, PASAPORTE).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
            @ApiResponse(responseCode = "400", description = "Tipo de documento inválido")
    })
    @GetMapping("/tipo-documento/{tipoDocumento}")
    @Tag(name = "Usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorTipoDocumento(@PathVariable String tipoDocumento) {
        if (!tipoDocumento.matches("RUT|PASAPORTE")) {
            return ResponseEntity.badRequest().build();
        }
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorTipoDocumento(tipoDocumento);
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por número de documento", description = "Este endpoint permite obtener usuarios según el número de documento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios con el número de documento especificado")
    })
    @GetMapping("/numero-documento/{numeroDocumento}")
    @Tag(name = "Usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorNumeroDocumento(@PathVariable String numeroDocumento) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorNumeroDocumento(numeroDocumento);
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuarios por fecha de nacimiento", description = "Este endpoint permite obtener una lista de usuarios según su fecha de nacimiento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
            @ApiResponse(responseCode = "400", description = "Fecha de nacimiento inválida")
    })
    @GetMapping("/fecha-nacimiento/{fechaNacimiento}")
    @Tag(name = "Usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorFechaNacimiento(@PathVariable String fechaNacimiento) {
        try {
            LocalDate fecha = LocalDate.parse(fechaNacimiento);
            List<Usuario> usuarios = usuarioService.obtenerUsuariosPorFechaNacimiento(fecha);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Eliminar un usuario", description = "Este endpoint permite eliminar un usuario del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    @Tag(name = "Usuarios")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
