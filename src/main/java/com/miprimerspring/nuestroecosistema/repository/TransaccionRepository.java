package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion,Long> {

    // Consulta específica para buscar transacciones por cuenta
    @Query("SELECT t FROM Transaccion t WHERE t.cuenta.cuentaId = :cuentaId")
    List<Transaccion> findByCuentaId(@Param("cuentaId") Integer cuentaId);

    // Consulta específica para buscar transacciones por cuenta de origen
    @Query("SELECT t FROM Transaccion t WHERE t.transaccionOrigen.cuentaId = :origenId")
    List<Transaccion> findByOrigenId(@Param("origenId") Integer origenId);

    // Consulta específica para buscar transacciones por cuenta de destino
    @Query("SELECT t FROM Transaccion t WHERE t.transaccionDestino.cuentaId = :destinoId")
    List<Transaccion> findByDestinoId(@Param("destinoId") Integer destinoId);

    // Consulta específica para buscar transacciones por monto
    @Query("SELECT t FROM Transaccion t WHERE t.transaccionMonto = :monto")
    List<Transaccion> findByMonto(@Param("monto") Double monto);

    // Consulta específica para buscar transacciones por fecha
    @Query("SELECT t FROM Transaccion t WHERE t.transaccionFecha = :fecha")
    List<Transaccion> findByFecha(@Param("fecha") Timestamp fecha);
}