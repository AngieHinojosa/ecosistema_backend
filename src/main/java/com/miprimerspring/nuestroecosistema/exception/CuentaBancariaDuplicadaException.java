package com.miprimerspring.nuestroecosistema.exception;

public class CuentaBancariaDuplicadaException extends RuntimeException {

    public CuentaBancariaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
