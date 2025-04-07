package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Vendedor;
import com.miprimerspring.nuestroecosistema.repository.VendedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;

    @Autowired
    public VendedorServiceImpl(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public List<Vendedor> obtenerTodosLosVendedores() {
        return vendedorRepository.findAll();
    }

    @Override
    public Vendedor obtenerVendedorPorId(Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedor.orElse(null);
    }

    @Override
    public Vendedor crearVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Vendedor actualizarVendedor(Long id, Vendedor vendedor) {
        if (vendedorRepository.existsById(id)) {
            vendedor.setVendedorId(id);
            return vendedorRepository.save(vendedor);
        }
        return null;
    }

    @Override
    public void eliminarVendedor(Long id) {
        if (vendedorRepository.existsById(id)) {
            vendedorRepository.deleteById(id);
        }
    }
}
