package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;
import com.miprimerspring.nuestroecosistema.repository.DescuentoAplicadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DescuentoAplicadoServiceImpl implements DescuentoAplicadoService {

    @Autowired
    private DescuentoAplicadoRepository descuentoAplicadoRepository;

    @Override
    public List<DescuentoAplicado> obtenerTodosLosDescuentosAplicados() {
        return descuentoAplicadoRepository.findAll();  // Devuelve todos los descuentos aplicados
    }

    @Override
    public DescuentoAplicado obtenerDescuentoAplicadoPorId(Long id) {
        Optional<DescuentoAplicado> descuentoAplicado = descuentoAplicadoRepository.findById(id);
        return descuentoAplicado.orElse(null);  // Si no existe, devuelve null
    }

    @Override
    public DescuentoAplicado crearDescuentoAplicado(DescuentoAplicado descuentoAplicado) {
        return descuentoAplicadoRepository.save(descuentoAplicado);  // Guarda el descuento aplicado
    }

    @Override
    public DescuentoAplicado actualizarDescuentoAplicado(Long id, DescuentoAplicado descuentoAplicado) {
        if (descuentoAplicadoRepository.existsById(id)) {
            descuentoAplicado.setDescuentoAplicadoId(id);  // En este caso, si no hay setId, elimina esta línea.
            return descuentoAplicadoRepository.save(descuentoAplicado);  // Actualiza el descuento aplicado
        }
        return null;  // Si no existe el descuento, devuelve null
    }

    @Override
    public void eliminarDescuentoAplicado(Long id) {
        if (descuentoAplicadoRepository.existsById(id)) {
            descuentoAplicadoRepository.deleteById(id);  // Elimina el descuento aplicado por su ID
        }
    }
}
