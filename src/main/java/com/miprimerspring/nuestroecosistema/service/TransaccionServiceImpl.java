package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.TransaccionDTO;
import com.miprimerspring.nuestroecosistema.mapper.TransaccionMapper;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.model.Transaccion;
import com.miprimerspring.nuestroecosistema.repository.CuentaBancariaRepository;
import com.miprimerspring.nuestroecosistema.repository.TransaccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final TransaccionMapper transaccionMapper;
    private final CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    public TransaccionServiceImpl(TransaccionRepository transaccionRepository,
                                  TransaccionMapper transaccionMapper,
                                  CuentaBancariaRepository cuentaBancariaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.transaccionMapper = transaccionMapper;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    @Override
    public TransaccionDTO crearTransaccion(TransaccionDTO transaccionDTO) throws Exception {
        // Buscar las cuentas necesarias antes de mapear
        CuentaBancaria cuenta = cuentaBancariaRepository.findById(transaccionDTO.getCuentaId())
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));

        CuentaBancaria transaccionOrigen = cuentaBancariaRepository.findById(transaccionDTO.getTransaccionOrigenId())
                .orElseThrow(() -> new Exception("Cuenta origen no encontrada"));

        CuentaBancaria transaccionDestino = cuentaBancariaRepository.findById(transaccionDTO.getTransaccionDestinoId())
                .orElseThrow(() -> new Exception("Cuenta destino no encontrada"));

        // Validar el saldo de la cuenta origen (para evitar sobregiros)
        if (transaccionDTO.getTransaccionMonto() > transaccionOrigen.getCuentaSaldo().doubleValue()) {
            throw new Exception("Saldo insuficiente en la cuenta origen");
        }

        // Restar monto de la cuenta origen
        transaccionOrigen.setCuentaSaldo(transaccionOrigen.getCuentaSaldo().subtract(BigDecimal.valueOf(transaccionDTO.getTransaccionMonto())));

        // Sumar monto a la cuenta destino
        transaccionDestino.setCuentaSaldo(transaccionDestino.getCuentaSaldo().add(BigDecimal.valueOf(transaccionDTO.getTransaccionMonto())));

        // Guardar las cuentas actualizadas
        cuentaBancariaRepository.save(transaccionOrigen);
        cuentaBancariaRepository.save(transaccionDestino);

        // Mapear el DTO a entidad (sin cuentas aún)
        Transaccion transaccion = transaccionMapper.toEntity(transaccionDTO);
        transaccion.setCuenta(cuenta);
        transaccion.setTransaccionOrigen(transaccionOrigen);
        transaccion.setTransaccionDestino(transaccionDestino);

        // Guardar la transacción
        transaccion = transaccionRepository.save(transaccion);

        // Mapear la entidad a DTO
        return transaccionMapper.toDTO(transaccion);
    }

    @Override
    public TransaccionDTO obtenerTransaccionPorId(Long id) {
        return transaccionRepository.findById(id)
                .map(transaccionMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorCuentaId(Integer cuentaId) {
        List<Transaccion> transacciones = transaccionRepository.findByCuentaId(cuentaId);
        return transacciones.stream().map(transaccionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorOrigenId(Integer origenId) {
        List<Transaccion> transacciones = transaccionRepository.findByOrigenId(origenId);
        return transacciones.stream().map(transaccionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorDestinoId(Integer destinoId) {
        List<Transaccion> transacciones = transaccionRepository.findByDestinoId(destinoId);
        return transacciones.stream().map(transaccionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorMonto(Double monto) {
        List<Transaccion> transacciones = transaccionRepository.findByMonto(monto);
        return transacciones.stream().map(transaccionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorFecha(Timestamp fecha) {
        List<Transaccion> transacciones = transaccionRepository.findByFecha(fecha);
        return transacciones.stream().map(transaccionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void eliminarTransaccion(Long id) {
        transaccionRepository.deleteById(id);
    }
}