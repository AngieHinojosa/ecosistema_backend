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
        if (entity == null) {
            return null; // Si la entidad es null, devolvemos null
        }

        CarritoDTO dto = new CarritoDTO();
        dto.setCarritoId(entity.getCarritoId());

        // Verificamos que el usuario no sea null antes de acceder a su ID
        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getUsuarioId());
        }

        dto.setCarritoAgregadoEn(entity.getCarritoAgregadoEn());

        // Verificamos que la lista de productos no sea null
        if (entity.getProductos() != null) {
            List<CarritoProductoDTO> productosDTO = entity.getProductos().stream()
                    .map(CarritoProductoMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setProductos(productosDTO);
        }

        return dto;
    }

    public static Carrito toEntity(CarritoDTO dto, Usuario usuario) {
        Carrito entity = new Carrito();
        entity.setCarritoId(dto.getCarritoId());

        // Verificamos que el usuario no sea null antes de asignarlo
        if (usuario != null) {
            entity.setUsuario(usuario);
        }

        entity.setCarritoAgregadoEn(dto.getCarritoAgregadoEn());

        // Los productos se agregan aparte con referencia al carrito
        return entity;
    }
}