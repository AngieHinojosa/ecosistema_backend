package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;

import java.util.List;
import java.util.Optional;

public interface CuentaBancariaService {

    CuentaBancaria saveCuenta(CuentaBancaria cuenta);
    List<CuentaBancaria> getAllCuentas();
    Optional<CuentaBancaria> getCuentaById(int id);
    void deleteCuenta(int id);

}
