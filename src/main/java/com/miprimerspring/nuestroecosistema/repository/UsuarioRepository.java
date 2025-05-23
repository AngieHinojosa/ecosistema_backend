package com.miprimerspring.nuestroecosistema.repository;

import com.miprimerspring.nuestroecosistema.exception.DocumentoDuplicadoException;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.usuarioCorreo = :correo")
    Optional<Usuario> findByUsuarioCorreo(String correo);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioEstado = :estado")
    List<Usuario> findByEstado(String estado);

    @Query("SELECT u FROM Usuario u WHERE :rol MEMBER OF u.roles")
    List<Usuario> findByRol(ERol rol);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioTipoDocumento = :tipoDocumento")
    List<Usuario> findByTipoDocumento(String tipoDocumento);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioNumeroDocumento = :numeroDocumento")
    Optional<Usuario> findByUsuarioNumeroDocumento(String numeroDocumento);

    @Query("SELECT u FROM Usuario u WHERE u.usuarioFechaNacimiento = :fechaNacimiento")
    List<Usuario> findByFechaNacimiento(LocalDate fechaNacimiento);

    boolean existsByUsuarioCorreo(String correo);

    // Validación combinada por correo o número de documento
    boolean existsByUsuarioCorreoOrUsuarioNumeroDocumento(String correo, String numeroDocumento);
}
