package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mensajeId;

    private String mensajeContenido;
    private Boolean mensajeLeido;
    private Timestamp mensajeEnviadoEn;

    @ManyToOne
    @JoinColumn(name = "emisor_id", nullable = false)
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario receptor;

    public Mensaje() {
    }

    public Mensaje(long mensajeId, String mensajeContenido, Boolean mensajeLeido, Timestamp mensajeEnviadoEn, Usuario emisor, Usuario receptor) {
        this.mensajeId = mensajeId;
        this.mensajeContenido = mensajeContenido;
        this.mensajeLeido = mensajeLeido;
        this.mensajeEnviadoEn = mensajeEnviadoEn;
        this.emisor = emisor;
        this.receptor = receptor;
    }

    public long getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(long mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getMensajeContenido() {
        return mensajeContenido;
    }

    public void setMensajeContenido(String mensajeContenido) {
        this.mensajeContenido = mensajeContenido;
    }

    public Boolean getMensajeLeido() {
        return mensajeLeido;
    }

    public void setMensajeLeido(Boolean mensajeLeido) {
        this.mensajeLeido = mensajeLeido;
    }

    public Timestamp getMensajeEnviadoEn() {
        return mensajeEnviadoEn;
    }

    public void setMensajeEnviadoEn(Timestamp mensajeEnviadoEn) {
        this.mensajeEnviadoEn = mensajeEnviadoEn;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "mensajeId=" + mensajeId +
                ", mensajeContenido='" + mensajeContenido + '\'' +
                ", mensajeLeido=" + mensajeLeido +
                ", mensajeEnviadoEn=" + mensajeEnviadoEn +
                ", emisor=" + emisor +
                ", receptor=" + receptor +
                '}';
    }
}
