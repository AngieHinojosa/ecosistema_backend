package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.mapper.CuentaBancariaMapper;
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

    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final CuentaBancariaMapper cuentaBancariaMapper;

    @Autowired
    public CuentaBancariaServiceImpl(CuentaBancariaRepository cuentaBancariaRepository, CuentaBancariaMapper cuentaBancariaMapper) {
        this.cuentaBancariaRepository = cuentaBancariaRepository;
        this.cuentaBancariaMapper = cuentaBancariaMapper;
    }

    @Override
    public CuentaBancariaDTO crearCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancaria cuentaBancaria = cuentaBancariaMapper.toEntity(cuentaBancariaDTO);
        CuentaBancaria savedCuentaBancaria = cuentaBancariaRepository.save(cuentaBancaria);
        return cuentaBancariaMapper.toDTO(savedCuentaBancaria);
    }

    @Override
    public CuentaBancariaDTO obtenerCuentaBancariaPorId(Long id) {
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta Bancaria no encontrada"));
        return cuentaBancariaMapper.toDTO(cuentaBancaria);
    }

    @Override
    public List<CuentaBancariaDTO> obtenerCuentasPorUsuario(Integer usuarioId) {
        List<CuentaBancaria> cuentas = cuentaBancariaRepository.findByUsuarioId(usuarioId);
        return cuentas.stream()
                .map(cuentaBancariaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CuentaBancariaDTO> obtenerTodasCuentas() {
        List<CuentaBancaria> cuentas = cuentaBancariaRepository.findAll();
        return cuentas.stream()
                .map(cuentaBancariaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaBancariaDTO actualizarCuentaBancaria(Long id, CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancaria cuentaExistente = cuentaBancariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta Bancaria no encontrada"));
        cuentaExistente = cuentaBancariaMapper.toEntity(cuentaBancariaDTO);
        cuentaExistente.setCuentaId(id);  // Mantener el ID
        CuentaBancaria updatedCuentaBancaria = cuentaBancariaRepository.save(cuentaExistente);
        return cuentaBancariaMapper.toDTO(updatedCuentaBancaria);
    }

    @Override
    public void eliminarCuentaBancaria(Long id) {
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta Bancaria no encontrada"));
        cuentaBancariaRepository.delete(cuentaBancaria);
    }
}