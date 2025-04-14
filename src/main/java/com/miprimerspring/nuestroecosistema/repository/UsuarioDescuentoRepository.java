package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioDescuentoRepository extends JpaRepository<UsuarioDescuento,Long> {

    // Consulta específica para buscar por usuario
    @Query("SELECT ud FROM UsuarioDescuento ud WHERE ud.usuario.usuarioId = :usuarioId")
    List<UsuarioDescuento> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

    // Consulta específica para buscar por descuento
    @Query("SELECT ud FROM UsuarioDescuento ud WHERE ud.descuento.descuentoId = :descuentoId")
    List<UsuarioDescuento> findByDescuentoId(@Param("descuentoId") Long descuentoId);

    // Consulta específica para buscar por usuario y descuento
    @Query("SELECT ud FROM UsuarioDescuento ud WHERE ud.usuario.usuarioId = :usuarioId AND ud.descuento.descuentoId = :descuentoId")
    UsuarioDescuento findByUsuarioAndDescuento(@Param("usuarioId") Integer usuarioId, @Param("descuentoId") Long descuentoId);
}