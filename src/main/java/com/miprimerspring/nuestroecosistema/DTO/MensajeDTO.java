package com.miprimerspring.nuestroecosistema.DTO;

import com.miprimerspring.nuestroecosistema.model.Mensaje;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MensajeDTO {

    private long mensajeId;
    private String mensajeContenido;
    private Boolean mensajeLeido;
    private Timestamp mensajeEnviadoEn;
    private UsuarioDTO emisor;
    private UsuarioDTO receptor;

    public MensajeDTO(Mensaje mensaje) {
        this.mensajeId = mensaje.getMensajeId();
        this.mensajeContenido = mensaje.getMensajeContenido();
        this.mensajeLeido = mensaje.getMensajeLeido();
        this.mensajeEnviadoEn = mensaje.getMensajeEnviadoEn();
        this.emisor = new UsuarioDTO(mensaje.getEmisor());  // Ahora funciona correctamente
        this.receptor = new UsuarioDTO(mensaje.getReceptor());  // Ahora funciona correctamente
    }
}