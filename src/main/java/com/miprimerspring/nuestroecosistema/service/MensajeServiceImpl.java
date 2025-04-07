package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Mensaje;
import com.miprimerspring.nuestroecosistema.repository.MensajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public Mensaje crearMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    @Override
    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeRepository.findAll();
    }

    @Override
    public Optional<Mensaje> obtenerMensajePorId(Long id) {
        return mensajeRepository.findById(id);
    }

    @Override
    public void eliminarMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }
}
