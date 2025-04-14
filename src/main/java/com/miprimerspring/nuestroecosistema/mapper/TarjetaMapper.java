package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.TarjetaDTO;
import com.miprimerspring.nuestroecosistema.model.Tarjeta;
import org.springframework.stereotype.Component;

@Component
public class TarjetaMapper {

    // Método para mapear de Tarjeta a TarjetaDTO
    public TarjetaDTO tarjetaToTarjetaDTO(Tarjeta tarjeta) {
        if (tarjeta == null) {
            return null;
        }

        TarjetaDTO tarjetaDTO = new TarjetaDTO();
        tarjetaDTO.setTarjetaId(tarjeta.getTarjetaId());
        tarjetaDTO.setUsuarioId(tarjeta.getUsuario().getUsuarioId());  // Asumiendo que el Usuario tiene un ID
        tarjetaDTO.setTarjetaTipo(tarjeta.getTarjetaTipo());
        tarjetaDTO.setTarjetaNumero(tarjeta.getTarjetaNumero());
        tarjetaDTO.setTarjetaExpiracion(tarjeta.getTarjetaExpiracion());
        tarjetaDTO.setTarjetaCvv(tarjeta.getTarjetaCvv());
        tarjetaDTO.setTarjetaEstado(tarjeta.getTarjetaEstado());
        return tarjetaDTO;
    }

    // Método para mapear de TarjetaDTO a Tarjeta
    public Tarjeta tarjetaDTOToTarjeta(TarjetaDTO tarjetaDTO) {
        if (tarjetaDTO == null) {
            return null;
        }

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setTarjetaId(tarjetaDTO.getTarjetaId());
        // Aquí necesitarías obtener el Usuario en base al usuarioId, lo cual podría implicar una llamada a la base de datos
        //tarjeta.setUsuario(usuario); // Esto depende de cómo estés gestionando la carga de usuarios
        tarjeta.setTarjetaTipo(tarjetaDTO.getTarjetaTipo());
        tarjeta.setTarjetaNumero(tarjetaDTO.getTarjetaNumero());
        tarjeta.setTarjetaExpiracion(tarjetaDTO.getTarjetaExpiracion());
        tarjeta.setTarjetaCvv(tarjetaDTO.getTarjetaCvv());
        tarjeta.setTarjetaEstado(tarjetaDTO.getTarjetaEstado());
        return tarjeta;
    }
}
