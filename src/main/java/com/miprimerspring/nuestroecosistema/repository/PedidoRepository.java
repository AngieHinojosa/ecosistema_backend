package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Pedido;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>, JpaSpecificationExecutor<Pedido> {

    // Consulta simple con JPQL
    @Query("SELECT p FROM Pedido p WHERE p.pedidoEstado = :estado")
    List<Pedido> buscarPorEstado(@Param("estado") String estado);

    // Métodos derivados
    List<Pedido> findByUsuario_UsuarioId(Integer usuarioId);
    List<Pedido> findByDireccion_DireccionId(Integer direccionId);
    List<Pedido> findByPedidoFecha(Date pedidoFecha);

    // Especificaciones básicas
    static Specification<Pedido> porUsuarioId(Integer usuarioId) {
        return (root, query, cb) -> cb.equal(root.get("usuario").get("usuarioId"), usuarioId);
    }

    static Specification<Pedido> porDireccionId(Integer direccionId) {
        return (root, query, cb) -> cb.equal(root.get("direccion").get("direccionId"), direccionId);
    }

    static Specification<Pedido> porEstado(String estado) {
        return (root, query, cb) -> cb.equal(root.get("pedidoEstado"), estado);
    }
}
