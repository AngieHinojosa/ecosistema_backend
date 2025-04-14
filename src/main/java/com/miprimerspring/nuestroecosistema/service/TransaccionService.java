package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.TransaccionDTO;
import com.miprimerspring.nuestroecosistema.model.Transaccion;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TransaccionService {

    TransaccionDTO crearTransaccion(TransaccionDTO transaccionDTO);
    TransaccionDTO obtenerTransaccionPorId(Long id);
    List<TransaccionDTO> obtenerTransaccionesPorCuentaId(Integer cuentaId);
    List<TransaccionDTO> obtenerTransaccionesPorOrigenId(Integer origenId);
    List<TransaccionDTO> obtenerTransaccionesPorDestinoId(Integer destinoId);
    List<TransaccionDTO> obtenerTransaccionesPorMonto(Double monto);
    List<TransaccionDTO> obtenerTransaccionesPorFecha(Timestamp fecha);
    void eliminarTransaccion(Long id);

}
