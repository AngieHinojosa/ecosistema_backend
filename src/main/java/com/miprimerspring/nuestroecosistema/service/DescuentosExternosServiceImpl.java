package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.repository.DescuentosExternosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DescuentosExternosServiceImpl implements  DescuentosExternosService {

    @Autowired
    private DescuentosExternosRepository descuentosExternosRepository;

    @Override
    public List<DescuentosExternos> obtenerTodosLosDescuentosExternos() {
        return descuentosExternosRepository.findAll();  // Devuelve todos los descuentos externos
    }

    @Override
    public DescuentosExternos obtenerDescuentoExternoPorId(Long id) {
        Optional<DescuentosExternos> descuentosExternos = descuentosExternosRepository.findById(id);
        return descuentosExternos.orElse(null);  // Si no existe, devuelve null
    }

    @Override
    public DescuentosExternos crearDescuentoExterno(DescuentosExternos descuentosExternos) {
        return descuentosExternosRepository.save(descuentosExternos);  // Guarda el descuento externo
    }

    @Override
    public DescuentosExternos actualizarDescuentoExterno(Long id, DescuentosExternos descuentosExternos) {
        if (descuentosExternosRepository.existsById(id)) {
            descuentosExternos.setDescuentoId(id);  // Establece el ID del descuento a actualizar
            return descuentosExternosRepository.save(descuentosExternos);  // Actualiza el descuento
        }
        return null;  // Si no existe, devuelve null
    }

    @Override
    public void eliminarDescuentoExterno(Long id) {
        if (descuentosExternosRepository.existsById(id)) {
            descuentosExternosRepository.deleteById(id);  // Elimina el descuento externo por su ID
        }
    }
}
