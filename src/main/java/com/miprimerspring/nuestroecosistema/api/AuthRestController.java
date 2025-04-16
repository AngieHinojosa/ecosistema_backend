package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.LoginDTO;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.security.JwtUtils;
import com.miprimerspring.nuestroecosistema.security.UserDetailsImpl;
import com.miprimerspring.nuestroecosistema.service.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        // Autenticación del usuario con los nuevos nombres de campo
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsuarioCorreo(),  // Usamos usuarioCorreo
                        loginRequest.getUsuarioContrasena()  // Usamos usuarioContrasena
                )
        );

        // Obtener detalles del usuario autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Generar el JWT
        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        // Devolver la respuesta con el JWT, usando el correo del usuario como email
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername()));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Usuario usuario) {
        // Verificar si el usuario ya existe por correo
        if (usuarioService.existeUsuarioPorCorreo(usuario.getUsuarioCorreo())) {
            return ResponseEntity.badRequest().body("Error: El email ya está en uso");
        }

        // Asignar roles y otras configuraciones antes de guardar
        Set<ERol> roles = new HashSet<>();
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            roles.add(ERol.ROLE_USER);  // Usamos ERol en lugar de Rol
        } else {
            // Aquí no necesitamos la clase Rol ni RolService, simplemente asignamos los valores del enum ERol
            usuario.getRoles().forEach(rol -> {
                if (rol != null) {
                    // Asignamos el rol directamente sin buscarlo en la base de datos, porque ahora usamos un enum
                    roles.add(rol);
                }
            });
        }

        // Establecer los roles del usuario
        usuario.setRoles(roles);

        // Codificar la contraseña
        usuario.setUsuarioContrasena(passwordEncoder.encode(usuario.getUsuarioContrasena()));

        // Establecer si es vendedor
        usuario.setUsuarioVendedor(false);

        // Llamar al servicio para guardar el nuevo usuario
        Usuario usuarioGuardado = usuarioService.crearUsuario(usuario); // Cambié de UsuarioDTO a Usuario

        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }

    // DTO para la respuesta del JWT
    @Data
    @AllArgsConstructor
    public static class JwtResponse {
        private String token;
        private String email;  // Aquí puedes mantener el correo como email en la respuesta
    }
}