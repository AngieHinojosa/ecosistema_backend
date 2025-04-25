package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.CarritoProductoDTO;
import com.miprimerspring.nuestroecosistema.model.CarritoProducto;
import com.miprimerspring.nuestroecosistema.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class CarritoProductoMapper {

    public static CarritoProductoDTO toDTO(CarritoProducto entity) {
        CarritoProductoDTO dto = new CarritoProductoDTO();
        dto.setId(entity.getId());
        dto.setProductoId(entity.getProducto().getProductoId());
        dto.setCantidad(entity.getCantidad());
        dto.setAgregadoEn(entity.getAgregadoEn());
        return dto;
    }

    public static CarritoProducto toEntity(CarritoProductoDTO dto, Producto producto) {
        CarritoProducto entity = new CarritoProducto();
        entity.setProducto(producto);
        entity.setCantidad(dto.getCantidad());
        entity.setAgregadoEn(dto.getAgregadoEn());
        // El carrito se asigna aparte (al agregarse a un carrito específico)
        return entity;
    }
}

