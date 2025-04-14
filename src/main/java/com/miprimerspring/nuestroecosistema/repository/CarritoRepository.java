package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    // Consulta personalizada usando @Query
    @Query("SELECT c FROM Carrito c WHERE c.usuario.usuarioId = :usuarioId")
    List<Carrito> findCarritosByUsuarioId(@Param("usuarioId") Long usuarioId);

    // Especificación para filtrar por usuarioId
    static Specification<Carrito> usuarioIdEquals(Long usuarioId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("usuario").get("usuarioId"), usuarioId);
    }

    // Especificación para filtrar por productoId
    static Specification<Carrito> productoIdEquals(Long productoId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("producto").get("productoId"), productoId);
    }

    // Especificación para filtrar por carritoAgregadoEn (fecha de agregado)
    static Specification<Carrito> carritoAgregadoEnAfter(java.time.LocalDateTime fecha) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("carritoAgregadoEn"), fecha);
    }

    // Especificación para filtrar por carritoCantidad
    static Specification<Carrito> carritoCantidadGreaterThan(Integer cantidad) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("carritoCantidad"), cantidad);
    }

}

