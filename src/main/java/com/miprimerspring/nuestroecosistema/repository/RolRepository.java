package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
