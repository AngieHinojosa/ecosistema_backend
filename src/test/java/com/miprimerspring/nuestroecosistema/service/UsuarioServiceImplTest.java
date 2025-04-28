package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.exception.CuentaBancariaDuplicadaException;
import com.miprimerspring.nuestroecosistema.exception.DocumentoDuplicadoException;
import com.miprimerspring.nuestroecosistema.mapper.UsuarioMapper;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.CuentaBancariaRepository;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void crearUsuario_ShouldCreateUsuarioAndAccount() {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setUsuarioNumeroDocumento("12345");
        usuario.setUsuarioCorreo("correo@example.com");

        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setCuentaNumero("12345");
        cuentaBancaria.setCuentaSaldo(BigDecimal.ZERO);

        // Comportamiento esperado de los mocks
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(cuentaBancariaRepository.save(any(CuentaBancaria.class))).thenReturn(cuentaBancaria);

        // Ejecución
        Usuario result = usuarioServiceImpl.crearUsuario(usuario);

        // Verificación
        assertNotNull(result);
        assertEquals(usuario, result);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(cuentaBancariaRepository, times(1)).save(any(CuentaBancaria.class));
    }

    @Test
    void registrarDesdeDTO_ShouldCreateUsuarioAndAccount() {
        // Datos de prueba
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNumeroDocumento("12345");
        dto.setCorreo("correo@example.com");

        Usuario usuario = new Usuario();
        usuario.setUsuarioNumeroDocumento("12345");
        usuario.setUsuarioCorreo("correo@example.com");

        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setCuentaNumero("12345");
        cuentaBancaria.setCuentaSaldo(BigDecimal.ZERO);

        // Comportamiento esperado de los mocks
        when(usuarioRepository.findByUsuarioNumeroDocumento(dto.getNumeroDocumento())).thenReturn(Optional.empty());
        when(usuarioRepository.findByUsuarioCorreo(dto.getCorreo())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(cuentaBancariaRepository.save(any(CuentaBancaria.class))).thenReturn(cuentaBancaria);

        // Ejecución
        Usuario result = usuarioServiceImpl.registrarDesdeDTO(dto);

        // Verificación
        assertNotNull(result);
        assertEquals(usuario, result);
        verify(usuarioRepository, times(1)).findByUsuarioNumeroDocumento(dto.getNumeroDocumento());
        verify(usuarioRepository, times(1)).findByUsuarioCorreo(dto.getCorreo());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(cuentaBancariaRepository, times(1)).save(any(CuentaBancaria.class));
    }

    @Test
    void registrarDesdeDTO_ShouldThrowDocumentoDuplicadoException_WhenDocumentoIsDuplicated() {
        // Datos de prueba
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNumeroDocumento("12345");

        // Comportamiento esperado de los mocks
        when(usuarioRepository.findByUsuarioNumeroDocumento(dto.getNumeroDocumento())).thenReturn(Optional.of(new Usuario()));

        // Ejecución y verificación
        assertThrows(DocumentoDuplicadoException.class, () -> usuarioServiceImpl.registrarDesdeDTO(dto));
    }

    @Test
    void registrarDesdeDTO_ShouldThrowDocumentoDuplicadoException_WhenCorreoIsDuplicated() {
        // Datos de prueba
        UsuarioDTO dto = new UsuarioDTO();
        dto.setCorreo("correo@example.com");

        // Comportamiento esperado de los mocks
        when(usuarioRepository.findByUsuarioCorreo(dto.getCorreo())).thenReturn(Optional.of(new Usuario()));

        // Ejecución y verificación
        assertThrows(DocumentoDuplicadoException.class, () -> usuarioServiceImpl.registrarDesdeDTO(dto));
    }


    @Test
    void obtenerUsuarioPorId_ShouldReturnUsuario_WhenExists() {
        // Datos de prueba
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(id);

        // Comportamiento esperado de los mocks
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Ejecución
        Optional<Usuario> result = usuarioServiceImpl.obtenerUsuarioPorId(id);

        // Verificación
        assertTrue(result.isPresent());
        assertEquals(usuario, result.get());
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void obtenerUsuarioPorId_ShouldReturnEmpty_WhenNotExists() {
        // Datos de prueba
        Long id = 1L;

        // Comportamiento esperado de los mocks
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        Optional<Usuario> result = usuarioServiceImpl.obtenerUsuarioPorId(id);

        // Verificación
        assertFalse(result.isPresent());
        verify(usuarioRepository, times(1)).findById(id);
    }
}
