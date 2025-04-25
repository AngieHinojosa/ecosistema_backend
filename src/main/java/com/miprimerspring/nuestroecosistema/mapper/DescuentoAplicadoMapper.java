package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.dto.PedidoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class DescuentoAplicadoMapper {

    public static DescuentoAplicadoDTO toDTO(DescuentoAplicado descuentoAplicado) {
        if (descuentoAplicado == null) {
            return null;
        }

        DescuentoAplicadoDTO dto = new DescuentoAplicadoDTO();
        dto.setDescuentoAplicadoId(descuentoAplicado.getDescuentoAplicadoId());
        dto.setPedidoId(descuentoAplicado.getPedido().getPedidoId());
        dto.setDescripcion(descuentoAplicado.getDescripcion());
        dto.setPorcentajeDescuento(descuentoAplicado.getPorcentajeDescuento());
        dto.setDescuentoAplicadoTotal(descuentoAplicado.getDescuentoAplicadoTotal());
        dto.setDescuentoCodigo(descuentoAplicado.getDescuentoCodigo());

        return dto;
    }

    public static DescuentoAplicado toEntity(DescuentoAplicadoDTO dto) {
        if (dto == null) {
            return null;
        }

        DescuentoAplicado descuento = new DescuentoAplicado();
        descuento.setDescuentoAplicadoId(dto.getDescuentoAplicadoId());
        // Aquí necesitarás setear la entidad 'Pedido' si es necesario
        descuento.setDescripcion(dto.getDescripcion());
        descuento.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        descuento.setDescuentoAplicadoTotal(dto.getDescuentoAplicadoTotal());
        descuento.setDescuentoCodigo(dto.getDescuentoCodigo());

        return descuento;
    }
}