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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        crearCuentaBancariaParaUsuario(nuevoUsuario);
        return nuevoUsuario;
    }

    @Override
    public Usuario registrarDesdeDTO(UsuarioDTO dto) {
        if (usuarioRepository.findByUsuarioNumeroDocumento(dto.getNumeroDocumento()).isPresent()) {
            throw new DocumentoDuplicadoException("Ya existe un usuario con el número de documento: " + dto.getNumeroDocumento());
        }

        if (usuarioRepository.findByUsuarioCorreo(dto.getCorreo()).isPresent()) {
            throw new DocumentoDuplicadoException("Ya existe un usuario con el correo electrónico: " + dto.getCorreo());
        }

        Usuario usuario = UsuarioMapper.toEntity(dto, passwordEncoder);
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        crearCuentaBancariaParaUsuario(nuevoUsuario);
        return nuevoUsuario;
    }

    private void crearCuentaBancariaParaUsuario(Usuario usuario) {
        String numeroCuenta = generarNumeroCuenta(usuario);

        if (cuentaBancariaRepository.existsByCuentaNumero(numeroCuenta)) {
            throw new CuentaBancariaDuplicadaException("Ya existe una cuenta bancaria registrada con el número de documento: " + numeroCuenta);
        }

        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setUsuario(usuario);
        cuentaBancaria.setCuentaTipo("debito");
        cuentaBancaria.setCuentaSaldo(BigDecimal.ZERO);
        cuentaBancaria.setCuentaNumero(numeroCuenta);
        cuentaBancariaRepository.save(cuentaBancaria);
    }

    private String generarNumeroCuenta(Usuario usuario) {
        return usuario.getUsuarioNumeroDocumento();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByUsuarioCorreo(correo);
    }

    @Override
    public boolean existeUsuarioPorCorreo(String correo) {
        return usuarioRepository.existsByUsuarioCorreo(correo);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorEstado(String estado) {
        return usuarioRepository.findByEstado(estado);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorRol(ERol rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorTipoDocumento(String tipoDocumento) {
        return usuarioRepository.findByTipoDocumento(tipoDocumento);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorNumeroDocumento(String numeroDocumento) {
        return usuarioRepository.findByUsuarioNumeroDocumento(numeroDocumento)
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public List<Usuario> obtenerUsuariosPorFechaNacimiento(LocalDate fechaNacimiento) {
        return usuarioRepository.findByFechaNacimiento(fechaNacimiento);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}