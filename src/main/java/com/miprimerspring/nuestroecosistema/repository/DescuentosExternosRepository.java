package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescuentosExternosRepository extends JpaRepository<DescuentosExternos, Integer>, JpaSpecificationExecutor<DescuentosExternos> {

    // Consulta simple con JPQL
    @Query("SELECT d FROM DescuentosExternos d WHERE d.descuentoCodigo = :codigo")
    List<DescuentosExternos> buscarPorCodigo(@Param("codigo") String codigo);

    // Métodos derivados
    List<DescuentosExternos> findByDescuentoActivo(Boolean descuentoActivo);

    // Especificaciones básicas
    static Specification<DescuentosExternos> porCodigo(String codigo) {
        return (root, query, cb) -> cb.equal(root.get("descuentoCodigo"), codigo);
    }

    static Specification<DescuentosExternos> porDescuentoActivo(Boolean descuentoActivo) {
        return (root, query, cb) -> cb.equal(root.get("descuentoActivo"), descuentoActivo);
    }
}
