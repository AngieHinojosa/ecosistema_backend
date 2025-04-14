package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Pago;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long>, JpaSpecificationExecutor<Pago> {

    // Consulta simple con JPQL
    @Query("SELECT p FROM Pago p WHERE p.pagoMetodo = :metodo")
    List<Pago> buscarPorMetodo(@Param("metodo") String metodo);

    // Métodos derivados
    List<Pago> findByPedido_PedidoId(Integer pedidoId);
    List<Pago> findByCuenta_CuentaId(Integer cuentaId);
    List<Pago> findByPagoFecha(java.sql.Timestamp pagoFecha);

    // Especificaciones básicas
    static Specification<Pago> porPedidoId(Integer pedidoId) {
        return (root, query, cb) -> cb.equal(root.get("pedido").get("pedidoId"), pedidoId);
    }

    static Specification<Pago> porCuentaId(Integer cuentaId) {
        return (root, query, cb) -> cb.equal(root.get("cuenta").get("cuentaId"), cuentaId);
    }

    static Specification<Pago> porMetodoPago(String metodoPago) {
        return (root, query, cb) -> cb.equal(root.get("pagoMetodo"), metodoPago);
    }

}
