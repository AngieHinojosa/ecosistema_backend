package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.PagoDTO;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.model.Pago;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public PagoDTO toDTO(Pago entity) {
        if (entity == null) {
            return null;
        }

        PagoDTO dto = new PagoDTO();
        dto.setPagoId(entity.getPagoId());
        dto.setPedidoId(entity.getPedido() != null ? entity.getPedido().getPedidoId() : null);
        dto.setCuentaId(entity.getCuenta() != null ? entity.getCuenta().getCuentaId() : null);
        dto.setPagoMonto(entity.getPagoMonto());
        dto.setPagoMetodo(entity.getPagoMetodo());
        dto.setPagoFecha(entity.getPagoFecha());

        return dto;
    }

    public Pago toEntity(PagoDTO dto) {
        if (dto == null) {
            return null;
        }

        Pago entity = new Pago();
        entity.setPagoId(dto.getPagoId());

        Pedido pedido = new Pedido();
        pedido.setPedidoId(dto.getPedidoId());
        entity.setPedido(pedido);

        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setCuentaId(dto.getCuentaId());
        entity.setCuenta(cuenta);

        entity.setPagoMonto(dto.getPagoMonto());
        entity.setPagoMetodo(dto.getPagoMetodo());
        entity.setPagoFecha(dto.getPagoFecha());

        return entity;
    }
}
