package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.LogsActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsActividadRepository extends JpaRepository<LogsActividad, Long> {
}
