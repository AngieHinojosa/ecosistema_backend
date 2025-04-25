package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PuntosFidelizacionRepository extends JpaRepository<PuntosFidelizacion, Long>, JpaSpecificationExecutor<PuntosFidelizacion> {

    // Consulta con JPQL
    @Query("SELECT p FROM PuntosFidelizacion p WHERE p.usuario.usuarioId = :usuarioId")
    List<PuntosFidelizacion> buscarPorUsuario(@Param("usuarioId") Integer usuarioId);

    // Especificaciones básicas
    static Specification<PuntosFidelizacion> porUsuarioId(Integer usuarioId) {
        return (root, query, cb) -> cb.equal(root.get("usuario").get("usuarioId"), usuarioId);
    }

    static Specification<PuntosFidelizacion> porCantidad(Integer cantidad) {
        return (root, query, cb) -> cb.equal(root.get("puntoCantidad"), cantidad);
    }

    static Specification<PuntosFidelizacion> porUltimaActualizacion(Timestamp ultimaActualizacion) {
        return (root, query, cb) -> cb.equal(root.get("puntoUltimaActualizacion"), ultimaActualizacion);
    }

}
