package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.repository.CuentaBancariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public List<CuentaBancariaDTO> obtenerTodasLasCuentaBancarias() {
        List<CuentaBancaria> cuentas = cuentaBancariaRepository.findAll();
        return cuentas.stream()
                .map(cuenta -> new CuentaBancariaDTO(
                        cuenta.getCuentaId(),
                        cuenta.getCuentaTipo(),
                        cuenta.getCuentaSaldo(),
                        cuenta.getUsuario().getUsuarioId()))  // Mapea la entidad a DTO
                .collect(Collectors.toList());
    }

    @Override
    public CuentaBancariaDTO obtenerCuentaBancariaPorId(Long id) {
        Optional<CuentaBancaria> cuentaBancariaOpt = cuentaBancariaRepository.findById(id);
        if (cuentaBancariaOpt.isPresent()) {
            CuentaBancaria cuenta = cuentaBancariaOpt.get();
            return new CuentaBancariaDTO(
                    cuenta.getCuentaId(),
                    cuenta.getCuentaTipo(),
                    cuenta.getCuentaSaldo(),
                    cuenta.getUsuario().getUsuarioId());
        }
        return null; // o lanzar excepción si lo prefieres
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
