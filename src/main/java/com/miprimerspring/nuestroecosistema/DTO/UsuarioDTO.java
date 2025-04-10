package com.miprimerspring.nuestroecosistema.DTO;

import com.miprimerspring.nuestroecosistema.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long usuarioId;
    private String usuarioNombres;
    private String usuarioApellidos;
    private String usuarioTelefono;
    private String usuarioContrasenia;

    // Constructor para convertir un Usuario en un UsuarioDTO
    public UsuarioDTO(Usuario usuario) {
        this.usuarioId = usuario.getUsuarioId();
        this.usuarioNombres = usuario.getUsuarioNombres();
        this.usuarioApellidos = usuario.getUsuarioApellidos();
        this.usuarioTelefono = usuario.getUsuarioTelefono();
        this.usuarioContrasenia = usuario.getUsuarioContrasenia();
    }

    public static UsuarioDTO fromEntity(Usuario usuario) {
        return new UsuarioDTO(usuario);
    }
}
