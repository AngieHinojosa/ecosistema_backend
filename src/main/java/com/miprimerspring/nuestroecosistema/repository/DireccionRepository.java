package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.model.Direccion;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer>, JpaSpecificationExecutor<Direccion> {

    // Consulta simple con JPQL
    @Query("SELECT d FROM Direccion d WHERE d.direccionCalle LIKE %:calle%")
    List<Direccion> buscarPorCalle(@Param("calle") String calle);

    // Métodos derivados
    List<Direccion> findByUsuario_UsuarioId(Integer usuarioId);

    // Especificaciones básicas
    static Specification<Direccion> porUsuarioId(Integer usuarioId) {
        return (root, query, cb) -> cb.equal(root.get("usuario").get("usuarioId"), usuarioId);
    }

    static Specification<Direccion> porCalle(String calle) {
        return (root, query, cb) -> cb.like(root.get("direccionCalle"), "%" + calle + "%");
    }

}
