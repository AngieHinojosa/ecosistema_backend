package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.mapper.UsuarioMapper;
import com.miprimerspring.nuestroecosistema.model.Rol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.RolRepository;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.rolRepository = rolRepository;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        // Obtener el rol usando el rolId desde el DTO
        Rol rol = rolRepository.findById(Math.toIntExact(usuarioDTO.getRolId())).orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // Pasamos tanto el UsuarioDTO como el Rol al método toEntity
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO, rol);

        // Guardamos el usuario y retornamos el DTO
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElse(null);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        return usuario != null ? usuarioMapper.toDTO(usuario) : null;
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorEstado(String estado) {
        List<Usuario> usuarios = usuarioRepository.findByEstado(estado);
        return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorRolId(Integer rolId) {
        List<Usuario> usuarios = usuarioRepository.findByRolId(Long.valueOf(rolId));
        return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorVendedor(Boolean vendedor) {
        List<Usuario> usuarios = usuarioRepository.findByVendedor(vendedor);
        return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorTipoDocumento(String tipoDocumento) {
        List<Usuario> usuarios = usuarioRepository.findByTipoDocumento(tipoDocumento);
        return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorNumeroDocumento(String numeroDocumento) {
        List<Usuario> usuarios = usuarioRepository.findByNumeroDocumento(numeroDocumento);
        return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorFechaNacimiento(String fechaNacimiento) {
        List<Usuario> usuarios = usuarioRepository.findByFechaNacimiento(fechaNacimiento);
        return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}