package com.miprimerspring.nuestroecosistema.exception;

import com.miprimerspring.nuestroecosistema.dto.MensajeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CuentaBancariaDuplicadaException.class)
    public ResponseEntity<MensajeResponse> handleCuentaDuplicada(CuentaBancariaDuplicadaException ex) {
        return ResponseEntity
                .badRequest()
                .body(new MensajeResponse("Error: " + ex.getMessage()));
    }

    @ExceptionHandler(CorreoExistenteException.class)
    public ResponseEntity<MensajeResponse> handleEmailDuplicado(CorreoExistenteException ex) {
        return ResponseEntity
                .badRequest()
                .body(new MensajeResponse("Error: " + ex.getMessage()));
    }

    @ExceptionHandler(PagoNotFoundException.class)
    public ResponseEntity<String> handlePagoNotFound(PagoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
