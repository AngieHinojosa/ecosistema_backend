package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rolId;

    @Column(name = "rol_nombre", length = 50, nullable = false)
    private String rolNombre;

    public Rol() {
    }

    public Rol(long rol_id, String rol_nombre) {
        this.rolId = rol_id;
        this.rolNombre = rol_nombre;
    }

    public long getRol_id() {
        return rolId;
    }

    public void setRol_id(long rol_id) {
        this.rolId = rol_id;
    }

    public String getRol_nombre() {
        return rolNombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rolNombre = rol_nombre;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "rol_id=" + rolId +
                ", rol_nombre='" + rolNombre + '\'' +
                '}';
    }
}
