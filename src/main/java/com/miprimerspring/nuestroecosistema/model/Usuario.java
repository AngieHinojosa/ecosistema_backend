package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "usuario_uuid", length = 36, columnDefinition = "CHAR(36) DEFAULT (uuid())")
    private String usuarioUuid = UUID.randomUUID().toString();

    @Column(name = "usuario_nombres", length = 100)
    private String usuarioNombres;

    @Column(name = "usuario_apellidos", length = 100)
    private String usuarioApellidos;

    @Column(name = "usuario_correo", length = 150, unique = true)
    private String usuarioCorreo;

    @Column(name = "usuario_telefono", length = 255)
    private String usuarioTelefono;

    @Column(name = "usuario_contrasena", columnDefinition = "TEXT")
    private String usuarioContrasena;

    @Column(name = "usuario_fecha_registro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp usuarioFechaRegistro = new Timestamp(System.currentTimeMillis());

    @Column(name = "usuario_estado", length = 20)
    private String usuarioEstado = "activo";

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Set<ERol> roles = new HashSet<>();

    @Column(name = "usuario_tipo_documento", length = 50)
    private String usuarioTipoDocumento;

    @Column(name = "usuario_numero_documento", length = 50)
    private String usuarioNumeroDocumento;

    @Column(name = "usuario_fecha_nacimiento")
    private Date usuarioFechaNacimiento;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Tarjeta> tarjetas;

    // Nuevo campo para marcar si el usuario es vendedor
    @Column(name = "usuario_vendedor")
    private Boolean usuarioVendedor = false;

    @PrePersist
    public void prePersist() {
        if (this.usuarioUuid == null) {
            this.usuarioUuid = UUID.randomUUID().toString();
        }
        if (this.usuarioFechaRegistro == null) {
            this.usuarioFechaRegistro = new Timestamp(System.currentTimeMillis());
        }
    }

    public Usuario(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuentaBancaria> cuentasBancarias = new ArrayList<>();
}