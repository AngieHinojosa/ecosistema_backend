package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.Rol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class UsuarioMapper {

    // Método para mapear de DTO a entidad
    public Usuario toEntity(UsuarioDTO usuarioDTO, Rol rol) {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioDTO.getUsuarioId());
        usuario.setUsuarioUuid(usuarioDTO.getUsuarioUuid());
        usuario.setUsuarioNombres(usuarioDTO.getUsuarioNombres());
        usuario.setUsuarioApellidos(usuarioDTO.getUsuarioApellidos());
        usuario.setUsuarioCorreo(usuarioDTO.getUsuarioCorreo());
        usuario.setUsuarioTelefono(usuarioDTO.getUsuarioTelefono());
        usuario.setUsuarioContrasena(usuarioDTO.getUsuarioContrasena());
        usuario.setUsuarioEstado(usuarioDTO.getUsuarioEstado());
        usuario.setUsuarioVendedor(usuarioDTO.getUsuarioVendedor());
        usuario.setRol(rol);  // Asignación del rol
        usuario.setUsuarioTipoDocumento(usuarioDTO.getUsuarioTipoDocumento());
        usuario.setUsuarioNumeroDocumento(usuarioDTO.getUsuarioNumeroDocumento());

        // Convertir la fecha de String a Date si es necesario
        usuario.setUsuarioFechaNacimiento(Date.valueOf(usuarioDTO.getUsuarioFechaNacimiento()));
        return usuario;
    }

    // Método para mapear de entidad a DTO
    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuarioId(usuario.getUsuarioId());
        usuarioDTO.setUsuarioUuid(usuario.getUsuarioUuid());
        usuarioDTO.setUsuarioNombres(usuario.getUsuarioNombres());
        usuarioDTO.setUsuarioApellidos(usuario.getUsuarioApellidos());
        usuarioDTO.setUsuarioCorreo(usuario.getUsuarioCorreo());
        usuarioDTO.setUsuarioTelefono(usuario.getUsuarioTelefono());
        usuarioDTO.setUsuarioContrasena(usuario.getUsuarioContrasena());
        usuarioDTO.setUsuarioEstado(usuario.getUsuarioEstado());
        usuarioDTO.setUsuarioVendedor(usuario.getUsuarioVendedor());
        usuarioDTO.setRolId(usuario.getRol().getRolId());  // Asignación del rolId
        usuarioDTO.setUsuarioTipoDocumento(usuario.getUsuarioTipoDocumento());
        usuarioDTO.setUsuarioNumeroDocumento(usuario.getUsuarioNumeroDocumento());

        // Convertir la fecha de Date a String
        usuarioDTO.setUsuarioFechaNacimiento(usuario.getUsuarioFechaNacimiento().toString());
        return usuarioDTO;
    }
}