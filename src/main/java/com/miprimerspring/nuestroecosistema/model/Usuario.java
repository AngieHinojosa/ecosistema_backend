package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "usuario_uuid", length = 36, columnDefinition = "CHAR(36) DEFAULT (uuid())")
    private String usuarioUuid;

    @Column(name = "usuario_nombres", length = 100, nullable = false)
    private String usuarioNombres;

    @Column(name = "usuario_apellidos", length = 100, nullable = false)
    private String usuarioApellidos;

    @Column(name = "usuario_correo", length = 150, nullable = false, unique = true)
    private String usuarioCorreo;

    @Column(name = "usuario_telefono", length = 255, nullable = false)
    private String usuarioTelefono;

    @Column(name = "usuario_contrasena", columnDefinition = "TEXT", nullable = false)
    private String usuarioContrasena;  // Eliminar 'usuarioContrasenia' (campo duplicado)

    @Column(name = "usuario_fecha_registro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp usuarioFechaRegistro;

    @Column(name = "usuario_estado", length = 20)
    private String usuarioEstado;

    @Column(name = "usuario_vendedor")
    private Boolean usuarioVendedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @Column(name = "usuario_tipo_documento", length = 50)
    private String usuarioTipoDocumento;

    @Column(name = "usuario_numero_documento", length = 50)
    private String usuarioNumeroDocumento;

    @Column(name = "usuario_fecha_nacimiento")
    private Date usuarioFechaNacimiento;

    // Relación OneToMany con Tarjeta
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Tarjeta> tarjetas;
}