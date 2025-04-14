package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor,Long> {

    // Consulta específica para buscar por usuario
    @Query("SELECT v FROM Vendedor v WHERE v.usuario.usuarioId = :usuarioId")
    Vendedor findByUsuarioId(@Param("usuarioId") Integer usuarioId);

    // Consulta específica para buscar por nombre de tienda
    @Query("SELECT v FROM Vendedor v WHERE v.vendedorNombreTienda LIKE %:nombreTienda%")
    List<Vendedor> findByVendedorNombreTienda(@Param("nombreTienda") String nombreTienda);

    // Consulta específica para buscar por estado de vendedor
    @Query("SELECT v FROM Vendedor v WHERE v.vendedorEstado = :estado")
    List<Vendedor> findByVendedorEstado(@Param("estado") String estado);
}