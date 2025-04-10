package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.PagosDTO;
import com.miprimerspring.nuestroecosistema.model.Pagos;
import com.miprimerspring.nuestroecosistema.repository.PagosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PagosServiceImpl implements PagosService {

    @Autowired
    private PagosRepository pagosRepository;

    @Override
    public PagosDTO crearPago(PagosDTO pagoDTO) {
        Pagos pago = new Pagos();
        pago.setPagoMonto(pagoDTO.getPagoMonto());
        pago.setPagoMetodo(pagoDTO.getPagoMetodo());
        pago.setPagoFecha(pagoDTO.getPagoFecha());
        // Asignar otros atributos como el Pedido y CuentaBancaria si es necesario

        Pagos nuevoPago = pagosRepository.save(pago);
        return new PagosDTO(nuevoPago.getPagoId(), nuevoPago.getPagoMonto(), nuevoPago.getPagoMetodo(), nuevoPago.getPagoFecha());
    }

    @Override
    public List<PagosDTO> obtenerTodosLosPagos() {
        List<Pagos> pagos = pagosRepository.findAll();
        return pagos.stream()
                .map(pago -> new PagosDTO(pago.getPagoId(), pago.getPagoMonto(), pago.getPagoMetodo(), pago.getPagoFecha()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PagosDTO> obtenerPagoPorId(Long id) {
        Optional<Pagos> pago = pagosRepository.findById(id);
        return pago.map(p -> new PagosDTO(p.getPagoId(), p.getPagoMonto(), p.getPagoMetodo(), p.getPagoFecha()));
    }

    @Override
    public void eliminarPago(Long id) {
        pagosRepository.deleteById(id);
    }
}
