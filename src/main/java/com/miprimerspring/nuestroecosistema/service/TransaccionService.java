package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Transaccion;

import java.util.List;
import java.util.Optional;

public interface TransaccionService {

    Transaccion saveTransaccion(Transaccion transaccion);
    List<Transaccion> getAllTransacciones();
    Optional<Transaccion> getTransaccionById(int id);
    void deleteTransaccion(int id);

}
