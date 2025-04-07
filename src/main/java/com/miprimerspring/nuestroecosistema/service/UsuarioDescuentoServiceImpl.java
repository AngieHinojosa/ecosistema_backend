package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;
import com.miprimerspring.nuestroecosistema.repository.UsuarioDescuentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioDescuentoServiceImpl implements UsuarioDescuentoService {

    private final UsuarioDescuentoRepository usuarioDescuentoRepository;

    @Autowired
    public UsuarioDescuentoServiceImpl(UsuarioDescuentoRepository usuarioDescuentoRepository) {
        this.usuarioDescuentoRepository = usuarioDescuentoRepository;
    }

    @Override
    public List<UsuarioDescuento> obtenerTodosLosUsuarioDescuentos() {
        return usuarioDescuentoRepository.findAll();
    }

    @Override
    public UsuarioDescuento obtenerUsuarioDescuentoPorId(Long id) {
        Optional<UsuarioDescuento> usuarioDescuento = usuarioDescuentoRepository.findById(id);
        return usuarioDescuento.orElse(null);
    }

    @Override
    public UsuarioDescuento crearUsuarioDescuento(UsuarioDescuento usuarioDescuento) {
        return usuarioDescuentoRepository.save(usuarioDescuento);
    }

    @Override
    public UsuarioDescuento actualizarUsuarioDescuento(Long id, UsuarioDescuento usuarioDescuento) {
        if (usuarioDescuentoRepository.existsById(id)) {
            usuarioDescuento.setUsuarioDescuentoId(id);
            return usuarioDescuentoRepository.save(usuarioDescuento);
        }
        return null;
    }

    @Override
    public void eliminarUsuarioDescuento(Long id) {
        if (usuarioDescuentoRepository.existsById(id)) {
            usuarioDescuentoRepository.deleteById(id);
        }
    }
}
