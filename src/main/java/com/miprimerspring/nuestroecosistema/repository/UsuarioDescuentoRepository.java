package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDescuentoRepository extends JpaRepository<UsuarioDescuento,Long> {

}
