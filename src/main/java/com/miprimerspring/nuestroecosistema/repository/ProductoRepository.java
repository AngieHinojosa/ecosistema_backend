package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Producto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {

    // Método derivado para obtener productos por usuarioId
    List<Producto> findByUsuario_UsuarioId(Long usuarioId);

    // Otros métodos derivados
    List<Producto> findByCategoria_CategoriaId(Integer categoriaId);
    List<Producto> findByProductoNombreContaining(String productoNombre);

    // Especificaciones básicas para búsqueda por nombre
    static Specification<Producto> porUsuarioId(Long usuarioId) {
        return (root, query, cb) -> cb.equal(root.get("usuario").get("usuarioId"), usuarioId);
    }

    static Specification<Producto> porCategoriaId(Integer categoriaId) {
        return (root, query, cb) -> cb.equal(root.get("categoria").get("categoriaId"), categoriaId);
    }

    static Specification<Producto> porNombreLike(String nombre) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("productoNombre")), "%" + nombre.toLowerCase() + "%");
    }
}