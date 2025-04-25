package com.miprimerspring.nuestroecosistema.exception;

public class PagoNotFoundException extends RuntimeException {

    public PagoNotFoundException(Long id) {
        super("No se encontró un pago con el ID: " + id);
    }

    public PagoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
