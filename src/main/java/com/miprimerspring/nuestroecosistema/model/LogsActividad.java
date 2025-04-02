package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs_actividad")
public class LogsActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private long logId;  // Identificador único

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;  // Usuario que realiza la acción

    @Column(name = "log_accion", nullable = false)
    private String logAccion;  // Descripción de la acción

    @Column(name = "log_fecha", nullable = false)
    private LocalDateTime logFecha;  // Fecha y hora de la acción

    @Column(name = "log_detalles", columnDefinition = "json")
    private String logDetalles;  // Detalles adicionales de la acción en formato JSON

    public LogsActividad() {
    }

    public LogsActividad(long logId, Usuario usuario, String logAccion, LocalDateTime logFecha, String logDetalles) {
        this.logId = logId;
        this.usuario = usuario;
        this.logAccion = logAccion;
        this.logFecha = logFecha;
        this.logDetalles = logDetalles;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLogAccion() {
        return logAccion;
    }

    public void setLogAccion(String logAccion) {
        this.logAccion = logAccion;
    }

    public LocalDateTime getLogFecha() {
        return logFecha;
    }

    public void setLogFecha(LocalDateTime logFecha) {
        this.logFecha = logFecha;
    }

    public String getLogDetalles() {
        return logDetalles;
    }

    public void setLogDetalles(String logDetalles) {
        this.logDetalles = logDetalles;
    }

    @Override
    public String toString() {
        return "LogsActividad{" +
                "logId=" + logId +
                ", usuario=" + usuario +
                ", logAccion='" + logAccion + '\'' +
                ", logFecha=" + logFecha +
                ", logDetalles='" + logDetalles + '\'' +
                '}';
    }
}
