package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.VendedorDTO;
import com.miprimerspring.nuestroecosistema.model.Vendedor;

import java.util.List;

public interface VendedorService {

    VendedorDTO crearVendedor(VendedorDTO vendedorDTO);
    VendedorDTO obtenerVendedorPorId(Long id);
    VendedorDTO obtenerVendedorPorUsuarioId(Integer usuarioId);
    List<VendedorDTO> obtenerVendedoresPorNombreTienda(String nombreTienda);
    List<VendedorDTO> obtenerVendedoresPorEstado(String estado);
    void eliminarVendedor(Long id);

}
