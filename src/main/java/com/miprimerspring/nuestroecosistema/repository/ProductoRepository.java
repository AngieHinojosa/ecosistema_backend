package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Producto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {

    // Consulta con JPQL
    @Query("SELECT p FROM Producto p WHERE p.productoEstado = :estado")
    List<Producto> buscarPorEstado(@Param("estado") String estado);

    // Métodos derivados
    List<Producto> findByVendedor_VendedorId(Integer vendedorId);
    List<Producto> findByCategoria_CategoriaId(Integer categoriaId);
    List<Producto> findByProductoNombreContaining(String productoNombre);

    // Especificaciones básicas
    static Specification<Producto> porVendedorId(Integer vendedorId) {
        return (root, query, cb) -> cb.equal(root.get("vendedor").get("vendedorId"), vendedorId);
    }

    static Specification<Producto> porCategoriaId(Integer categoriaId) {
        return (root, query, cb) -> cb.equal(root.get("categoria").get("categoriaId"), categoriaId);
    }

    static Specification<Producto> porEstado(String estado) {
        return (root, query, cb) -> cb.equal(root.get("productoEstado"), estado);
    }

    static Specification<Producto> porNombreLike(String nombre) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("productoNombre")), "%" + nombre.toLowerCase() + "%");
    }
}
