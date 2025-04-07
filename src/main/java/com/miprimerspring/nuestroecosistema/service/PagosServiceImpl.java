package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Pagos;
import com.miprimerspring.nuestroecosistema.repository.PagosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PagosServiceImpl implements PagosService {

    @Autowired
    private PagosRepository pagosRepository;

    @Override
    public Pagos crearPago(Pagos pago) {
        return pagosRepository.save(pago);
    }

    @Override
    public List<Pagos> obtenerTodosLosPagos() {
        return pagosRepository.findAll();
    }

    @Override
    public Optional<Pagos> obtenerPagoPorId(Long id) {
        return pagosRepository.findById(id);
    }

    @Override
    public void eliminarPago(Long id) {
        pagosRepository.deleteById(id);
    }
}
