package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.usuarioCorreo = :correo")
    Usuario findByCorreo(@Param("correo") String correo);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioEstado = :estado")
    List<Usuario> findByEstado(@Param("estado") String estado);

    @Query("SELECT u FROM Usuario u WHERE u.rol.rolId = :rolId")
    List<Usuario> findByRolId(@Param("rolId") Long rolId);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioVendedor = :vendedor")
    List<Usuario> findByVendedor(@Param("vendedor") Boolean vendedor);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioTipoDocumento = :tipoDocumento")
    List<Usuario> findByTipoDocumento(@Param("tipoDocumento") String tipoDocumento);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioNumeroDocumento = :numeroDocumento")
    List<Usuario> findByNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioFechaNacimiento = :fechaNacimiento")
    List<Usuario> findByFechaNacimiento(@Param("fechaNacimiento") String fechaNacimiento);
}