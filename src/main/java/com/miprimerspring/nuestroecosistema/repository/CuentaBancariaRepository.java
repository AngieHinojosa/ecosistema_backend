package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaBancariaRepository  extends JpaRepository<CuentaBancaria, Long> {
}
