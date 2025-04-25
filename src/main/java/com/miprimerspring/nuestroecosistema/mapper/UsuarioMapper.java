package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto, PasswordEncoder passwordEncoder) {
        Usuario usuario = new Usuario();
        usuario.setUsuarioNombres(dto.getNombres());
        usuario.setUsuarioApellidos(dto.getApellidos());
        usuario.setUsuarioCorreo(dto.getCorreo());
        usuario.setUsuarioTelefono(dto.getTelefono());
        usuario.setUsuarioContrasena(passwordEncoder.encode(dto.getContrasena()));
        usuario.setUsuarioTipoDocumento(dto.getTipoDocumento());
        usuario.setUsuarioNumeroDocumento(dto.getNumeroDocumento());
        usuario.setUsuarioFechaNacimiento(dto.getFechaNacimiento());

        // Roles
        Set<ERol> roles = new HashSet<>();
        roles.add(ERol.ROLE_USER);
        if (dto.isEsVendedor()) {
            roles.add(ERol.ROLE_VENDEDOR);
        }
        usuario.setRoles(roles);

        return usuario;
    }
}
