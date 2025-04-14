package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.LogsActividad;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsActividadRepository extends JpaRepository<LogsActividad, Long>, JpaSpecificationExecutor<LogsActividad> {

    // Consulta simple con JPQL
    @Query("SELECT l FROM LogsActividad l WHERE l.logAccion = :accion")
    List<LogsActividad> buscarPorAccion(@Param("accion") String accion);

    // Métodos derivados
    List<LogsActividad> findByUsuario_UsuarioId(Integer usuarioId);
    List<LogsActividad> findByLogAccion(String logAccion);

    // Especificaciones básicas
    static Specification<LogsActividad> porUsuarioId(Integer usuarioId) {
        return (root, query, cb) -> cb.equal(root.get("usuario").get("usuarioId"), usuarioId);
    }

    static Specification<LogsActividad> porLogAccion(String logAccion) {
        return (root, query, cb) -> cb.equal(root.get("logAccion"), logAccion);
    }
}
