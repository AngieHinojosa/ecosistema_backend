package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Categoria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Consulta personalizada: buscar por nombre exacto
    @Query("SELECT c FROM Categoria c WHERE c.categoriaNombre = :nombre")
    List<Categoria> findByNombre(@Param("nombre") String nombre);

    // Consulta personalizada: buscar por palabra clave en la descripción
    @Query("SELECT c FROM Categoria c WHERE LOWER(c.categoriaDescripcion) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Categoria> findByDescripcionContaining(@Param("keyword") String keyword);

    // Especificación para nombre exacto
    static Specification<Categoria> nombreEquals(String nombre) {
        return (root, query, cb) -> cb.equal(root.get("categoriaNombre"), nombre);
    }

    // Especificación para descripción que contenga palabra clave
    static Specification<Categoria> descripcionContiene(String keyword) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("categoriaDescripcion")), "%" + keyword.toLowerCase() + "%");
    }
}
