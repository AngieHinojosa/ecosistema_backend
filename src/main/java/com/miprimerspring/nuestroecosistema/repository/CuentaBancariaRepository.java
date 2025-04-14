package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaBancariaRepository  extends JpaRepository<CuentaBancaria, Long>, JpaSpecificationExecutor<CuentaBancaria> {

    // Buscar por número exacto
    @Query("SELECT c FROM CuentaBancaria c WHERE c.cuentaNumero = :numero")
    CuentaBancaria findByCuentaNumero(@Param("numero") String numero);

    // Buscar todas las cuentas de un usuario
    @Query("SELECT c FROM CuentaBancaria c WHERE c.usuario.usuarioId = :usuarioId")
    List<CuentaBancaria> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

    // Especificación: por ID de usuario
    static Specification<CuentaBancaria> usuarioIdEquals(Integer usuarioId) {
        return (root, query, cb) -> cb.equal(root.get("usuario").get("usuarioId"), usuarioId);
    }

    // Especificación: tipo de cuenta exacto
    static Specification<CuentaBancaria> tipoCuentaEquals(String tipo) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("cuentaTipo")), tipo.toLowerCase());
    }

    // Especificación: saldo mínimo
    static Specification<CuentaBancaria> saldoMayorA(Double monto) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("cuentaSaldo"), monto);
    }

    // Especificación: saldo menor
    static Specification<CuentaBancaria> saldoMenorA(Double monto) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("cuentaSaldo"), monto);
    }

    // Buscar por usuario y tipo
    default List<CuentaBancaria> findByUsuarioYTipo(Integer usuarioId, String tipo) {
        return findAll(Specification.where(usuarioIdEquals(usuarioId))
                .and(tipoCuentaEquals(tipo)));
    }

    // Buscar cuentas con saldo mayor a un monto
    default List<CuentaBancaria> findConSaldoMayorA(Double monto) {
        return findAll(saldoMayorA(monto));
    }

    // Buscar cuentas con saldo menor a un monto
    default List<CuentaBancaria> findConSaldoMenorA(Double monto) {
        return findAll(saldoMenorA(monto));
    }
}
