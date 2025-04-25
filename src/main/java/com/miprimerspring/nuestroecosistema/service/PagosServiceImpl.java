package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PagoDTO;
import com.miprimerspring.nuestroecosistema.exception.PagoNotFoundException;
import com.miprimerspring.nuestroecosistema.mapper.PagoMapper;
import com.miprimerspring.nuestroecosistema.model.Pago;
import com.miprimerspring.nuestroecosistema.repository.PagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PagosServiceImpl implements PagosService {

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;

    @Autowired
    public PagosServiceImpl(PagoRepository pagoRepository, PagoMapper pagoMapper) {
        this.pagoRepository = pagoRepository;
        this.pagoMapper = pagoMapper;
    }

    @Override
    public PagoDTO crearPago(PagoDTO pagoDTO) {
        Pago pago = pagoMapper.toEntity(pagoDTO);
        Pago savedPago = pagoRepository.save(pago);
        return pagoMapper.toDTO(savedPago);
    }

    @Override
    public PagoDTO obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id)
                .map(pago -> pagoMapper.toDTO(pago))  // Aquí se usa la instancia de pagoMapper
                .orElseThrow(() -> new PagoNotFoundException(id));  // Lanza la excepción personalizada si no se encuentra el pago
    }

    @Override
    public List<PagoDTO> obtenerPagosPorPedidoId(Integer pedidoId) {
        List<Pago> pagos = pagoRepository.findByPedido_PedidoId(pedidoId);
        return pagos.stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> obtenerPagosPorCuentaId(Integer cuentaId) {
        List<Pago> pagos = pagoRepository.findByCuenta_CuentaId(cuentaId);
        return pagos.stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> obtenerPagosPorMetodo(String metodo) {
        List<Pago> pagos = pagoRepository.buscarPorMetodo(metodo);
        return pagos.stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> obtenerTodosPagos() {
        List<Pago> pagos = pagoRepository.findAll();
        return pagos.stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PagoDTO actualizarPago(Long id, PagoDTO pagoDTO) {
        Pago pagoExistente = pagoRepository.findById(id)
                .orElseThrow(() -> new PagoNotFoundException(id));  // Lanzar excepción si el pago no existe
        pagoExistente = pagoMapper.toEntity(pagoDTO);
        pagoExistente.setPagoId(id);  // Asegurarse de que el ID no se sobrescriba
        Pago updatedPago = pagoRepository.save(pagoExistente);
        return pagoMapper.toDTO(updatedPago);
    }

    @Override
    public void eliminarPago(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new PagoNotFoundException(id));  // Lanzar excepción si el pago no existe
        pagoRepository.delete(pago);
    }
}