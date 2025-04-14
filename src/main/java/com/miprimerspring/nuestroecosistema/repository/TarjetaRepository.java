package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {

    // Consulta específica para buscar por usuario
    @Query("SELECT t FROM Tarjeta t WHERE t.usuario.usuarioId = :usuarioId")
    List<Tarjeta> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

    // Consulta específica para buscar por estado de tarjeta
    @Query("SELECT t FROM Tarjeta t WHERE t.tarjetaEstado = :estado")
    List<Tarjeta> findByEstado(@Param("estado") String estado);

    // Consulta específica para buscar por tipo de tarjeta
    @Query("SELECT t FROM Tarjeta t WHERE t.tarjetaTipo = :tipo")
    List<Tarjeta> findByTipo(@Param("tipo") String tipo);

    // Consulta específica para buscar por número de tarjeta
    @Query("SELECT t FROM Tarjeta t WHERE t.tarjetaNumero = :numero")
    Tarjeta findByNumero(@Param("numero") String numero);
}
