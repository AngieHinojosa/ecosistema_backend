package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
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
    public List<Usuario> obtenerUsuariosPorRol(ERol rol) {  // Usamos ERol en lugar de rolId
        return usuarioRepository.findByRol(rol);  // Ajustamos a la consulta que ahora usa ERol
    }

    @Override
    public List<Usuario> obtenerUsuariosPorVendedor(Boolean vendedor) {
        return usuarioRepository.findByVendedor(vendedor);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorTipoDocumento(String tipoDocumento) {
        return usuarioRepository.findByTipoDocumento(tipoDocumento);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorNumeroDocumento(String numeroDocumento) {
        return usuarioRepository.findByNumeroDocumento(numeroDocumento);
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