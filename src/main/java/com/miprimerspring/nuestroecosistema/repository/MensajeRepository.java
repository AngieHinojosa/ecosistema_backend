package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Mensaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>, JpaSpecificationExecutor<Mensaje> {

    // Consulta simple con JPQL
    @Query("SELECT m FROM Mensaje m WHERE m.emisor.usuarioId = :emisorId AND m.receptor.usuarioId = :receptorId")
    List<Mensaje> buscarPorEmisorYReceptor(@Param("emisorId") Integer emisorId, @Param("receptorId") Integer receptorId);

    // Métodos derivados
    List<Mensaje> findByEmisor_UsuarioId(Integer emisorId);
    List<Mensaje> findByReceptor_UsuarioId(Integer receptorId);
    List<Mensaje> findByMensajeLeido(Boolean mensajeLeido);

    // Especificaciones básicas
    static Specification<Mensaje> porEmisorId(Integer emisorId) {
        return (root, query, cb) -> cb.equal(root.get("emisor").get("usuarioId"), emisorId);
    }

    static Specification<Mensaje> porReceptorId(Integer receptorId) {
        return (root, query, cb) -> cb.equal(root.get("receptor").get("usuarioId"), receptorId);
    }

    // Mensajes enviados por un usuario al soporte
    List<Mensaje> findByEmisor_UsuarioIdAndReceptor_UsuarioId(Integer emisorId, Integer receptorId);

    // Mensajes recibidos por un usuario desde el soporte
    List<Mensaje> findByReceptor_UsuarioIdAndEmisor_UsuarioId(Integer receptorId, Integer emisorId);

}
