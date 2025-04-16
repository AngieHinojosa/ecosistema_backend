package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "usuario_uuid", length = 36, columnDefinition = "CHAR(36) DEFAULT (uuid())")
    private String usuarioUuid = UUID.randomUUID().toString(); // Generar UUID por defecto

    @Column(name = "usuario_nombres", length = 100, nullable = false)
    private String usuarioNombres;

    @NotNull
    @Column(name = "usuario_apellidos", length = 100, nullable = false)
    private String usuarioApellidos;

    @Column(name = "usuario_correo", length = 150, nullable = false, unique = true)
    private String usuarioCorreo;

    @Column(name = "usuario_telefono", length = 255, nullable = true)
    private String usuarioTelefono;

    @Column(name = "usuario_contrasena", columnDefinition = "TEXT")
    private String usuarioContrasena;

    @Column(name = "usuario_fecha_registro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp usuarioFechaRegistro = new Timestamp(System.currentTimeMillis());  // Asignar la fecha de registro actual

    @Column(name = "usuario_estado", length = 20, nullable = false)
    private String usuarioEstado = "activo"; // Valor por defecto

    @Column(name = "usuario_vendedor")
    private Boolean usuarioVendedor = false; // Valor por defecto

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Enumerated(EnumType.STRING)  // Guardamos los roles como STRING en la base de datos
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

    // Constructor para establecer valores por defecto si no se especifican
    @PrePersist
    public void prePersist() {
        if (this.usuarioUuid == null) {
            this.usuarioUuid = UUID.randomUUID().toString();
        }
        if (this.usuarioFechaRegistro == null) {
            this.usuarioFechaRegistro = new Timestamp(System.currentTimeMillis());
        }
    }
}