package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.model.PedidoDetalle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle,Long>, JpaSpecificationExecutor<PedidoDetalle> {

    // Consulta simple con JPQL
    @Query("SELECT pd FROM PedidoDetalle pd WHERE pd.pedido.pedidoId = :pedidoId")
    List<PedidoDetalle> buscarPorPedido(@Param("pedidoId") Integer pedidoId);

    // Métodos derivados
    List<PedidoDetalle> findByPedido_PedidoId(Integer pedidoId);
    List<PedidoDetalle> findByProducto_ProductoId(Integer productoId);

    // Especificaciones básicas
    static Specification<PedidoDetalle> porPedidoId(Integer pedidoId) {
        return (root, query, cb) -> cb.equal(root.get("pedido").get("pedidoId"), pedidoId);
    }

    static Specification<PedidoDetalle> porProductoId(Integer productoId) {
        return (root, query, cb) -> cb.equal(root.get("producto").get("productoId"), productoId);
    }
}
