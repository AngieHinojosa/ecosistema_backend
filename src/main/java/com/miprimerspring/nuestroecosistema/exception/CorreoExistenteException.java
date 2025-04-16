package com.miprimerspring.nuestroecosistema.exception;

public class CorreoExistenteException extends RuntimeException {

    public CorreoExistenteException(String correo) {
        super("Ya existe un usuario con el correo: " + correo);
    }
}
