package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.TransaccionDTO;
import com.miprimerspring.nuestroecosistema.model.Transaccion;
import org.springframework.stereotype.Component;

@Component
public class TransaccionMapper {

    // Método para mapear de DTO a entidad
    public Transaccion toEntity(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = new Transaccion();
        transaccion.setTransaccionId(transaccionDTO.getTransaccionId());
        transaccion.setTransaccionMonto(transaccionDTO.getTransaccionMonto());
        transaccion.setTransaccionFecha(transaccionDTO.getTransaccionFecha());
        return transaccion;
    }

    // Método para mapear de entidad a DTO
    public TransaccionDTO toDTO(Transaccion transaccion) {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setTransaccionId(transaccion.getTransaccionId());
        transaccionDTO.setTransaccionMonto(transaccion.getTransaccionMonto());
        transaccionDTO.setTransaccionFecha(transaccion.getTransaccionFecha());
        return transaccionDTO;
    }
}