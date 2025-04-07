package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Direccion;
import com.miprimerspring.nuestroecosistema.repository.DireccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public List<Direccion> obtenerTodasLasDirecciones() {
        return direccionRepository.findAll();  // Devuelve todas las direcciones
    }

    @Override
    public Direccion obtenerDireccionPorId(Long id) {
        Optional<Direccion> direccion = direccionRepository.findById(id);
        return direccion.orElse(null);  // Si no existe, devuelve null
    }

    @Override
    public Direccion crearDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);  // Guarda la dirección
    }

    @Override
    public Direccion actualizarDireccion(Long id, Direccion direccion) {
        if (direccionRepository.existsById(id)) {
            direccion.setDireccionId(id);  // Establece el ID de la dirección a actualizar
            return direccionRepository.save(direccion);  // Actualiza la dirección
        }
        return null;  // Si no existe, devuelve null
    }

    @Override
    public void eliminarDireccion(Long id) {
        if (direccionRepository.existsById(id)) {
            direccionRepository.deleteById(id);  // Elimina la dirección por su ID
        }
    }

}

