package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usuarioId;

    @Column(name = "usuario_nombres", length = 100, nullable = false)
    private String usuarioNombres;

    @Column(name = "usuario_apellidos", length = 100, nullable = false)
    private String usuarioApellidos;

    @Column(name = "usuario_telefono", nullable = false)
    private String usuarioTelefono;

    @Column(name = "usuario_contrasenia", nullable = false)
    private String usuarioContrasenia;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(long usuarioId, String usuarioNombres, String usuarioApellidos, String usuarioTelefono, String usuarioContrasenia, Rol rol) {
        this.usuarioId = usuarioId;
        this.usuarioNombres = usuarioNombres;
        this.usuarioApellidos = usuarioApellidos;
        this.usuarioTelefono = usuarioTelefono;
        this.usuarioContrasenia = usuarioContrasenia;
        this.rol = rol;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombres() {
        return usuarioNombres;
    }

    public void setUsuarioNombres(String usuarioNombres) {
        this.usuarioNombres = usuarioNombres;
    }

    public String getUsuarioApellidos() {
        return usuarioApellidos;
    }

    public void setUsuarioApellidos(String usuarioApellidos) {
        this.usuarioApellidos = usuarioApellidos;
    }

    public String getUsuarioTelefono() {
        return usuarioTelefono;
    }

    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }

    public String getUsuarioContrasenia() {
        return usuarioContrasenia;
    }

    public void setUsuarioContrasenia(String usuarioContrasenia) {
        this.usuarioContrasenia = usuarioContrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", usuarioNombres='" + usuarioNombres + '\'' +
                ", usuarioApellidos='" + usuarioApellidos + '\'' +
                ", usuarioTelefono='" + usuarioTelefono + '\'' +
                ", usuarioContrasenia='" + usuarioContrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
}
