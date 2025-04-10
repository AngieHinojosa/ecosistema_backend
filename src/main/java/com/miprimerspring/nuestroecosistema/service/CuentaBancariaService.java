package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;

import java.util.List;
import java.util.Optional;

public interface CuentaBancariaService {

    List<CuentaBancariaDTO> obtenerTodasLasCuentaBancarias();

    CuentaBancariaDTO obtenerCuentaBancariaPorId(Long id);

    CuentaBancaria crearCuentaBancaria(CuentaBancaria cuentaBancaria);

    CuentaBancaria actualizarCuentaBancaria(Long id, CuentaBancaria cuentaBancaria);

    void eliminarCuentaBancaria(Long id);
}
