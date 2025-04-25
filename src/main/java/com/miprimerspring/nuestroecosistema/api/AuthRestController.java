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

    // Endpoint para el login
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsuarioCorreo(),
                        loginRequest.getUsuarioContrasena()
                )
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername()));
    }

    // Endpoint para el registro de usuarios
    @PostMapping("/registro")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Usuario usuario,
                                          @RequestParam Boolean vendedor,
                                          @RequestParam(required = false, defaultValue = "false") Boolean admin) {
        if (usuarioService.existeUsuarioPorCorreo(usuario.getUsuarioCorreo())) {
            return ResponseEntity.badRequest().body("Error: El email ya está en uso");
        }

        Set<ERol> roles = new HashSet<>();
        roles.add(ERol.ROLE_USER);

        if (vendedor) {
            roles.add(ERol.ROLE_VENDEDOR);
        }

        if (admin) {
            roles.add(ERol.ROLE_ADMIN);
        }

        usuario.setRoles(roles);
        usuario.setUsuarioContrasena(passwordEncoder.encode(usuario.getUsuarioContrasena()));
        usuario.setUsuarioVendedor(vendedor);

        Usuario usuarioGuardado = usuarioService.crearUsuario(usuario);

        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }

    @Data
    @AllArgsConstructor
    public static class JwtResponse {
        private String token;
        private String email;
    }
}