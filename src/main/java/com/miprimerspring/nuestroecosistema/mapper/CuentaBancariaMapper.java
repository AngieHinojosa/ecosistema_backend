package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CuentaBancariaMapper {

    // Convertir CuentaBancaria a CuentaBancariaDTO
    public CuentaBancariaDTO toDTO(CuentaBancaria cuenta) {
        if (cuenta == null) {
            return null;
        }

        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        dto.setCuentaId(cuenta.getCuentaId());
        dto.setUsuarioId(cuenta.getUsuario() != null ? cuenta.getUsuario().getUsuarioId() : null);
        dto.setCuentaTipo(cuenta.getCuentaTipo());
        dto.setCuentaNumero(cuenta.getCuentaNumero());
        dto.setCuentaSaldo(cuenta.getCuentaSaldo());

        return dto;
    }

    // Convertir CuentaBancariaDTO a CuentaBancaria
    public CuentaBancaria toEntity(CuentaBancariaDTO dto) {
        if (dto == null) {
            return null;
        }

        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setCuentaId(dto.getCuentaId());

        // Asignar el Usuario
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(dto.getUsuarioId());
        cuenta.setUsuario(usuario);

        cuenta.setCuentaTipo(dto.getCuentaTipo());
        cuenta.setCuentaNumero(dto.getCuentaNumero());
        cuenta.setCuentaSaldo(dto.getCuentaSaldo());

        return cuenta;
    }
}