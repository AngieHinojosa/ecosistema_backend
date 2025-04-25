package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.ImagenProductoDTO;
import com.miprimerspring.nuestroecosistema.model.ImagenProducto;
import com.miprimerspring.nuestroecosistema.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ImagenProductoMapper {

    public ImagenProductoDTO toDTO(ImagenProducto entity) {
        if (entity == null) {
            return null;
        }

        ImagenProductoDTO dto = new ImagenProductoDTO();
        dto.setImagenId(entity.getImagenId());
        dto.setProductoId(entity.getProducto() != null ? entity.getProducto().getProductoId() : null);
        dto.setImagenUrl(entity.getImagenUrl());
        dto.setImagenEsPrincipal(entity.getImagenEsPrincipal());

        return dto;
    }

    public ImagenProducto toEntity(ImagenProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        ImagenProducto entity = new ImagenProducto();
        entity.setImagenId(dto.getImagenId());

        Producto producto = new Producto();
        producto.setProductoId(dto.getProductoId());
        entity.setProducto(producto);

        entity.setImagenUrl(dto.getImagenUrl());
        entity.setImagenEsPrincipal(dto.getImagenEsPrincipal());

        return entity;
    }
}
