package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.VendedorDTO;
import com.miprimerspring.nuestroecosistema.mapper.VendedorMapper;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.model.Vendedor;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import com.miprimerspring.nuestroecosistema.repository.VendedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;
    private final VendedorMapper vendedorMapper;
    private final UsuarioRepository usuarioRepository; // Necesitamos esto para obtener el Usuario

    @Autowired
    public VendedorServiceImpl(VendedorRepository vendedorRepository, VendedorMapper vendedorMapper, UsuarioRepository usuarioRepository) {
        this.vendedorRepository = vendedorRepository;
        this.vendedorMapper = vendedorMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public VendedorDTO crearVendedor(VendedorDTO vendedorDTO) {
        // Obtener el Usuario por su ID (vendedorDTO.getUsuarioId())
        Usuario usuario = usuarioRepository.findById(vendedorDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Usamos el mapper para mapear el DTO a la entidad Vendedor
        Vendedor vendedor = vendedorMapper.toEntity(vendedorDTO, usuario);

        // Guardar el Vendedor en la base de datos
        vendedor = vendedorRepository.save(vendedor);

        // Devolver el DTO del Vendedor
        return vendedorMapper.toDTO(vendedor);
    }

    @Override
    public VendedorDTO obtenerVendedorPorId(Long id) {
        return vendedorRepository.findById(id)
                .map(vendedorMapper::toDTO)
                .orElse(null);
    }

    @Override
    public VendedorDTO obtenerVendedorPorUsuarioId(Integer usuarioId) {
        Vendedor vendedor = vendedorRepository.findByUsuarioId(usuarioId);
        return vendedor != null ? vendedorMapper.toDTO(vendedor) : null;
    }

    @Override
    public List<VendedorDTO> obtenerVendedoresPorNombreTienda(String nombreTienda) {
        List<Vendedor> vendedores = vendedorRepository.findByVendedorNombreTienda(nombreTienda);
        return vendedores.stream().map(vendedorMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<VendedorDTO> obtenerVendedoresPorEstado(String estado) {
        List<Vendedor> vendedores = vendedorRepository.findByVendedorEstado(estado);
        return vendedores.stream().map(vendedorMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void eliminarVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }
}