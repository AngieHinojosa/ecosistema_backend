package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;
import com.miprimerspring.nuestroecosistema.repository.PuntosFidelizacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PuntosFidelizacionServiceImpl implements PuntosFidelizacionService {

    private final PuntosFidelizacionRepository puntosFidelizacionRepository;

    @Autowired
    public PuntosFidelizacionServiceImpl(PuntosFidelizacionRepository puntosFidelizacionRepository) {
        this.puntosFidelizacionRepository = puntosFidelizacionRepository;
    }

    @Override
    public List<PuntosFidelizacion> obtenerTodosLosPuntos() {
        return puntosFidelizacionRepository.findAll();
    }

    @Override
    public PuntosFidelizacion obtenerPuntosPorId(Long id) {
        Optional<PuntosFidelizacion> puntos = puntosFidelizacionRepository.findById(id);
        return puntos.orElse(null);
    }

    @Override
    public PuntosFidelizacion crearPuntos(PuntosFidelizacion puntosFidelizacion) {
        return puntosFidelizacionRepository.save(puntosFidelizacion);
    }

    @Override
    public PuntosFidelizacion actualizarPuntos(Long id, PuntosFidelizacion puntosFidelizacion) {
        if (puntosFidelizacionRepository.existsById(id)) {
            puntosFidelizacion.setPuntoId(id);
            return puntosFidelizacionRepository.save(puntosFidelizacion);
        }
        return null;
    }

    @Override
    public void eliminarPuntos(Long id) {
        if (puntosFidelizacionRepository.existsById(id)) {
            puntosFidelizacionRepository.deleteById(id);
        }
    }
}
