package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.PedidoDetalle;
import com.miprimerspring.nuestroecosistema.repository.PedidoDetalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

    private final PedidoDetalleRepository pedidoDetalleRepository;

    @Autowired
    public PedidoDetalleServiceImpl(PedidoDetalleRepository pedidoDetalleRepository) {
        this.pedidoDetalleRepository = pedidoDetalleRepository;
    }

    @Override
    public List<PedidoDetalle> obtenerTodosLosDetalles() {
        return pedidoDetalleRepository.findAll();
    }

    @Override
    public PedidoDetalle obtenerDetallePorId(Long id) {
        Optional<PedidoDetalle> detalle = pedidoDetalleRepository.findById(id);
        return detalle.orElse(null);
    }

    @Override
    public PedidoDetalle crearDetalle(PedidoDetalle pedidoDetalle) {
        return pedidoDetalleRepository.save(pedidoDetalle);
    }

    @Override
    public void eliminarDetalle(Long id) {
        if (pedidoDetalleRepository.existsById(id)) {
            pedidoDetalleRepository.deleteById(id);
        }
    }
}
