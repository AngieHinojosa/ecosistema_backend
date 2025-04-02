package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "puntos_fidelizacion")
public class PuntosFidelizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long puntoId;

    private Integer puntoCantidad;
    private Timestamp puntoUltimaActualizacion;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public PuntosFidelizacion() {
    }

    public PuntosFidelizacion(long puntoId, Integer puntoCantidad, Timestamp puntoUltimaActualizacion, Usuario usuario) {
        this.puntoId = puntoId;
        this.puntoCantidad = puntoCantidad;
        this.puntoUltimaActualizacion = puntoUltimaActualizacion;
        this.usuario = usuario;
    }

    public long getPuntoId() {
        return puntoId;
    }

    public void setPuntoId(long puntoId) {
        this.puntoId = puntoId;
    }

    public Integer getPuntoCantidad() {
        return puntoCantidad;
    }

    public void setPuntoCantidad(Integer puntoCantidad) {
        this.puntoCantidad = puntoCantidad;
    }

    public Timestamp getPuntoUltimaActualizacion() {
        return puntoUltimaActualizacion;
    }

    public void setPuntoUltimaActualizacion(Timestamp puntoUltimaActualizacion) {
        this.puntoUltimaActualizacion = puntoUltimaActualizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "PuntosFidelizacion{" +
                "puntoId=" + puntoId +
                ", puntoCantidad=" + puntoCantidad +
                ", puntoUltimaActualizacion=" + puntoUltimaActualizacion +
                ", usuario=" + usuario +
                '}';
    }
}
