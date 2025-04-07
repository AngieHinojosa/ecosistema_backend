package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Vendedor;

import java.util.List;

public interface VendedorService {

    List<Vendedor> obtenerTodosLosVendedores();
    Vendedor obtenerVendedorPorId(Long id);
    Vendedor crearVendedor(Vendedor vendedor);
    Vendedor actualizarVendedor(Long id, Vendedor vendedor);
    void eliminarVendedor(Long id);

}
