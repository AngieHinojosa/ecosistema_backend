package com.miprimerspring.nuestroecosistema.exception;

public class CuentaBancariaNotFoundException extends RuntimeException {

    public CuentaBancariaNotFoundException(String message) {
        super(message);
    }
}
