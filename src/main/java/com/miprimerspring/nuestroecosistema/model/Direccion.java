package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long direccionId;

    @Column(name = "direccion_tipo", length = 20)
    private String direccionTipo;

    @Column(name = "direccion_calle")
    private String direccionCalle;

    @Column(name = "direccion_numero")
    private String direccionNumero;

    @Column(name = "direccion_comuna")
    private String direccionComuna;

    @Column(name = "direccion_ciudad")
    private String direccionCiudad;

    @Column(name = "direccion_region")
    private String direccionRegion;

    @Column(name = "direccion_codigo_postal")
    private String direccionCodigoPostal;

    @Column(name = "direccion_pais")
    private String direccionPais;

    @Column(name = "direccion_activa")
    private Boolean direccionActiva = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Direccion() {
    }

    public Direccion(long direccionId, String direccionTipo, String direccionCalle, String direccionNumero, String direccionComuna, String direccionCiudad, String direccionRegion, String direccionCodigoPostal, String direccionPais, Boolean direccionActiva, Usuario usuario) {
        this.direccionId = direccionId;
        this.direccionTipo = direccionTipo;
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.direccionComuna = direccionComuna;
        this.direccionCiudad = direccionCiudad;
        this.direccionRegion = direccionRegion;
        this.direccionCodigoPostal = direccionCodigoPostal;
        this.direccionPais = direccionPais;
        this.direccionActiva = direccionActiva;
        this.usuario = usuario;
    }

    public long getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(long direccionId) {
        this.direccionId = direccionId;
    }

    public String getDireccionTipo() {
        return direccionTipo;
    }

    public void setDireccionTipo(String direccionTipo) {
        this.direccionTipo = direccionTipo;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getDireccionNumero() {
        return direccionNumero;
    }

    public void setDireccionNumero(String direccionNumero) {
        this.direccionNumero = direccionNumero;
    }

    public String getDireccionComuna() {
        return direccionComuna;
    }

    public void setDireccionComuna(String direccionComuna) {
        this.direccionComuna = direccionComuna;
    }

    public String getDireccionCiudad() {
        return direccionCiudad;
    }

    public void setDireccionCiudad(String direccionCiudad) {
        this.direccionCiudad = direccionCiudad;
    }

    public String getDireccionRegion() {
        return direccionRegion;
    }

    public void setDireccionRegion(String direccionRegion) {
        this.direccionRegion = direccionRegion;
    }

    public String getDireccionCodigoPostal() {
        return direccionCodigoPostal;
    }

    public void setDireccionCodigoPostal(String direccionCodigoPostal) {
        this.direccionCodigoPostal = direccionCodigoPostal;
    }

    public String getDireccionPais() {
        return direccionPais;
    }

    public void setDireccionPais(String direccionPais) {
        this.direccionPais = direccionPais;
    }

    public Boolean getDireccionActiva() {
        return direccionActiva;
    }

    public void setDireccionActiva(Boolean direccionActiva) {
        this.direccionActiva = direccionActiva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "direccionId=" + direccionId +
                ", direccionTipo='" + direccionTipo + '\'' +
                ", direccionCalle='" + direccionCalle + '\'' +
                ", direccionNumero='" + direccionNumero + '\'' +
                ", direccionComuna='" + direccionComuna + '\'' +
                ", direccionCiudad='" + direccionCiudad + '\'' +
                ", direccionRegion='" + direccionRegion + '\'' +
                ", direccionCodigoPostal='" + direccionCodigoPostal + '\'' +
                ", direccionPais='" + direccionPais + '\'' +
                ", direccionActiva=" + direccionActiva +
                ", usuario=" + usuario +
                '}';
    }
}
