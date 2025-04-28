package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.MensajeDTO;
import com.miprimerspring.nuestroecosistema.mapper.MensajeMapper;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Mensaje;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.MensajeRepository;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MensajeServiceImplTest {

    @InjectMocks
    private MensajeServiceImpl mensajeService;

    @Mock
    private MensajeRepository mensajeRepository;

    @Mock
    private MensajeMapper mensajeMapper;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private Usuario usuarioAutenticado;

    private MensajeDTO mensajeDTO;
    private Mensaje mensaje;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Datos de prueba
        mensajeDTO = new MensajeDTO();
        mensajeDTO.setMensajeContenido("Contenido del mensaje");

        mensaje = new Mensaje();
        mensaje.setMensajeContenido("Contenido del mensaje");

        // Simular un usuario autenticado con el rol adecuado
        when(usuarioAutenticado.getRoles()).thenReturn(java.util.Set.of(ERol.ROLE_ADMIN));

        // Simular el SecurityContextHolder
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(usuarioAutenticado);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void obtenerMensajePorId() {
        // Simula el comportamiento del repositorio
        when(mensajeRepository.findById(1L)).thenReturn(java.util.Optional.of(mensaje));

        // Simula que el mapper convierte la entidad en DTO
        when(mensajeMapper.toDTO(mensaje)).thenReturn(mensajeDTO);

        // Ejecuta el método
        MensajeDTO result = mensajeService.obtenerMensajePorId(1L);

        // Verifica los resultados
        assertNotNull(result);
        assertEquals("Contenido del mensaje", result.getMensajeContenido());
        verify(mensajeRepository, times(1)).findById(1L);
    }
}