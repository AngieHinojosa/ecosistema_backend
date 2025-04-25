package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;

import java.util.List;

public interface CuentaBancariaService {

    CuentaBancariaDTO crearCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO);

    CuentaBancariaDTO obtenerCuentaBancariaPorId(Long id);

    List<CuentaBancariaDTO> obtenerCuentasPorUsuario(Long usuarioId); // Cambio de Integer a Long

    List<CuentaBancariaDTO> obtenerTodasCuentas();

    CuentaBancariaDTO actualizarCuentaBancaria(Long id, CuentaBancariaDTO cuentaBancariaDTO);

    void eliminarCuentaBancaria(Long id);
}
