package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescuentoAplicadoRepository extends JpaRepository<DescuentoAplicado, Long>, JpaSpecificationExecutor<DescuentoAplicado> {

    // Consulta simple con JPQL
    @Query("SELECT d FROM DescuentoAplicado d WHERE d.descuentoCodigo = :codigo")
    List<DescuentoAplicado> buscarPorCodigo(@Param("codigo") String codigo);

    // Métodos derivados
    List<DescuentoAplicado> findByPedido_PedidoId(Integer pedidoId);
    List<DescuentoAplicado> findByDescuentosExternos_DescuentoId(Integer descuentoId);

    // Especificaciones básicas
    static Specification<DescuentoAplicado> porPedidoId(Integer pedidoId) {
        return (root, query, cb) -> cb.equal(root.get("pedido").get("pedidoId"), pedidoId);
    }

    static Specification<DescuentoAplicado> porDescuentoId(Integer descuentoId) {
        return (root, query, cb) -> cb.equal(root.get("descuentosExternos").get("descuentoId"), descuentoId);
    }
}
