package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.repository.CuentaBancariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public List<CuentaBancaria> obtenerTodasLasCuentaBancarias() {
        return cuentaBancariaRepository.findAll(); // Devuelve todas las cuentas bancarias
    }

    @Override
    public CuentaBancaria obtenerCuentaBancariaPorId(Long id) {
        Optional<CuentaBancaria> cuentaBancaria = cuentaBancariaRepository.findById(id);
        return cuentaBancaria.orElse(null);  // Si no existe, devuelve null
    }

    @Override
    public CuentaBancaria crearCuentaBancaria(CuentaBancaria cuentaBancaria) {
        return cuentaBancariaRepository.save(cuentaBancaria);  // Guarda la cuenta bancaria
    }

    @Override
    public CuentaBancaria actualizarCuentaBancaria(Long id, CuentaBancaria cuentaBancaria) {
        if (cuentaBancariaRepository.existsById(id)) {
            cuentaBancaria.setCuentaId(id);  // Establece el ID para la actualización
            return cuentaBancariaRepository.save(cuentaBancaria);  // Actualiza la cuenta bancaria
        }
        return null;  // Si no existe la cuenta, devuelve null
    }

    @Override
    public void eliminarCuentaBancaria(Long id) {
        if (cuentaBancariaRepository.existsById(id)) {
            cuentaBancariaRepository.deleteById(id);  // Elimina la cuenta bancaria por su ID
        }
    }

}
