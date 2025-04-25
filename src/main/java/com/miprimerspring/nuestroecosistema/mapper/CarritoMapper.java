package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
import com.miprimerspring.nuestroecosistema.dto.CarritoProductoDTO;
import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CarritoMapper {

    public static CarritoDTO toDTO(Carrito entity) {
        CarritoDTO dto = new CarritoDTO();
        dto.setCarritoId(entity.getCarritoId());
        dto.setUsuarioId(entity.getUsuario().getUsuarioId());
        dto.setCarritoAgregadoEn(entity.getCarritoAgregadoEn());

        List<CarritoProductoDTO> productosDTO = entity.getProductos().stream()
                .map(CarritoProductoMapper::toDTO)
                .collect(Collectors.toList());
        dto.setProductos(productosDTO);

        return dto;
    }

    public static Carrito toEntity(CarritoDTO dto, Usuario usuario) {
        Carrito entity = new Carrito();
        entity.setCarritoId(dto.getCarritoId());
        entity.setUsuario(usuario);
        entity.setCarritoAgregadoEn(dto.getCarritoAgregadoEn());
        // Los productos se agregan aparte con referencia al carrito
        return entity;
    }
}