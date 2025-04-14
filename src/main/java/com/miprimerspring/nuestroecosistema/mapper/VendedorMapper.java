package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.VendedorDTO;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.model.Vendedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
public class VendedorMapper {

    // Método para mapear de DTO a entidad
    public Vendedor toEntity(VendedorDTO vendedorDTO, Usuario usuario) {
        Vendedor vendedor = new Vendedor();

        vendedor.setVendedorId(vendedorDTO.getVendedorId());
        vendedor.setUsuario(usuario);  // Asignamos el objeto Usuario correspondiente
        vendedor.setVendedorNombreTienda(vendedorDTO.getVendedorNombreTienda());
        vendedor.setVendedorDescripcion(vendedorDTO.getVendedorDescripcion());
        vendedor.setVendedorLogoUrl(vendedorDTO.getVendedorLogoUrl());
        vendedor.setVendedorFechaCreacion(vendedorDTO.getVendedorFechaCreacion());
        vendedor.setVendedorEstado(vendedorDTO.getVendedorEstado());

        return vendedor;
    }

    // Método para mapear de entidad a DTO
    public VendedorDTO toDTO(Vendedor vendedor) {
        VendedorDTO vendedorDTO = new VendedorDTO();

        vendedorDTO.setVendedorId(vendedor.getVendedorId());
        vendedorDTO.setUsuarioId(vendedor.getUsuario().getUsuarioId());  // Usamos solo el ID del Usuario
        vendedorDTO.setVendedorNombreTienda(vendedor.getVendedorNombreTienda());
        vendedorDTO.setVendedorDescripcion(vendedor.getVendedorDescripcion());
        vendedorDTO.setVendedorLogoUrl(vendedor.getVendedorLogoUrl());
        vendedorDTO.setVendedorFechaCreacion(vendedor.getVendedorFechaCreacion());
        vendedorDTO.setVendedorEstado(vendedor.getVendedorEstado());

        return vendedorDTO;
    }
}