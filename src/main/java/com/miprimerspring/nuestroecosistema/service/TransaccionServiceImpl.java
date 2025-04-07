package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Transaccion;
import com.miprimerspring.nuestroecosistema.repository.TransaccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;

    @Autowired
    public TransaccionServiceImpl(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public List<Transaccion> obtenerTodasLasTransacciones() {
        return transaccionRepository.findAll();
    }

    @Override
    public Transaccion obtenerTransaccionPorId(Long id) {
        Optional<Transaccion> transaccion = transaccionRepository.findById(id);
        return transaccion.orElse(null);
    }

    @Override
    public Transaccion crearTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Override
    public Transaccion actualizarTransaccion(Long id, Transaccion transaccion) {
        if (transaccionRepository.existsById(id)) {
            transaccion.setTransaccionId(id);
            return transaccionRepository.save(transaccion);
        }
        return null;
    }

    @Override
    public void eliminarTransaccion(Long id) {
        if (transaccionRepository.existsById(id)) {
            transaccionRepository.deleteById(id);
        }
    }
}
