package com.miprimerspring.nuestroecosistema.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miprimerspring.nuestroecosistema.dto.LoginDTO;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.security.JwtUtils;
import com.miprimerspring.nuestroecosistema.security.UserDetailsImpl;
import com.miprimerspring.nuestroecosistema.security.UserDetailsServiceImpl;
import com.miprimerspring.nuestroecosistema.service.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AuthRestController.class)
class AuthRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UsuarioServiceImpl usuarioService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void authenticateUser_ShouldReturnJwtToken() throws Exception {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsuarioCorreo("test@example.com");
        loginDTO.setUsuarioContrasena("password123");

        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("test@example.com");
        when(jwtUtils.generateJwtToken(userDetails)).thenReturn("fake-jwt-token");

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake-jwt-token"))
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void registerUser_ShouldReturnCreatedUsuario() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsuarioCorreo("nuevo@example.com");
        usuario.setUsuarioContrasena("password123");
        usuario.setUsuarioNumeroDocumento("12345678");

        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setUsuarioCorreo("nuevo@example.com");
        usuarioGuardado.setUsuarioContrasena("encodedPassword");

        when(usuarioService.existeUsuarioPorCorreo(usuario.getUsuarioCorreo())).thenReturn(false);
        when(passwordEncoder.encode(usuario.getUsuarioContrasena())).thenReturn("encodedPassword");
        when(usuarioService.crearUsuario(any(Usuario.class))).thenReturn(usuarioGuardado);

        // Act & Assert
        mockMvc.perform(post("/api/auth/registro")
                        .param("vendedor", "true")
                        .param("admin", "false")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuarioCorreo").value("nuevo@example.com"));

        verify(usuarioService, times(1)).crearUsuario(any(Usuario.class));
    }

    @Test
    void registerUser_ShouldReturnBadRequest_WhenEmailExists() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsuarioCorreo("exists@example.com");
        usuario.setUsuarioContrasena("password123");

        when(usuarioService.existeUsuarioPorCorreo(usuario.getUsuarioCorreo())).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/auth/registro")
                        .param("vendedor", "false")
                        .param("admin", "false")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: El email ya está en uso"));

        verify(usuarioService, never()).crearUsuario(any(Usuario.class));
    }
}
