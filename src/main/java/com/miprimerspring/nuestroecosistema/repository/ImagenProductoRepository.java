package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.ImagenProducto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long>, JpaSpecificationExecutor<ImagenProducto> {

    // Consulta simple con JPQL
    @Query("SELECT i FROM ImagenProducto i WHERE i.imagenEsPrincipal = :principal")
    List<ImagenProducto> buscarPorPrincipal(@Param("principal") Boolean principal);

    // Métodos derivados
    List<ImagenProducto> findByProducto_ProductoId(Integer productoId);
    List<ImagenProducto> findByImagenEsPrincipal(Boolean imagenEsPrincipal);

    // Especificaciones básicas
    static Specification<ImagenProducto> porProductoId(Integer productoId) {
        return (root, query, cb) -> cb.equal(root.get("producto").get("productoId"), productoId);
    }

    static Specification<ImagenProducto> porImagenEsPrincipal(Boolean imagenEsPrincipal) {
        return (root, query, cb) -> cb.equal(root.get("imagenEsPrincipal"), imagenEsPrincipal);
    }
}
