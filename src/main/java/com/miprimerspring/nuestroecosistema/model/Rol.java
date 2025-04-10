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

    public Rol(long rolId, String rolNombre) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
    }

    public long getRolId() {
        return rolId;
    }

    public void setRolId(long rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;  // Asegúrate de tener este método
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "rolId=" + rolId +
                ", rolNombre='" + rolNombre + '\'' +
                '}';
    }
}