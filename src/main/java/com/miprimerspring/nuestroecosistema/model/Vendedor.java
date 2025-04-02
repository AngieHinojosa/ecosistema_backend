package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "vendedores")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vendedorId;

    private String vendedorNombreTienda;
    private String vendedorDescripcion;
    private String vendedorLogoUrl;
    private Timestamp vendedorFechaCreacion;
    private String vendedorEstado;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Vendedor() {
    }

    public Vendedor(long vendedorId, String vendedorNombreTienda, String vendedorDescripcion, String vendedorLogoUrl, Timestamp vendedorFechaCreacion, String vendedorEstado, Usuario usuario) {
        this.vendedorId = vendedorId;
        this.vendedorNombreTienda = vendedorNombreTienda;
        this.vendedorDescripcion = vendedorDescripcion;
        this.vendedorLogoUrl = vendedorLogoUrl;
        this.vendedorFechaCreacion = vendedorFechaCreacion;
        this.vendedorEstado = vendedorEstado;
        this.usuario = usuario;
    }

    public long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public String getVendedorNombreTienda() {
        return vendedorNombreTienda;
    }

    public void setVendedorNombreTienda(String vendedorNombreTienda) {
        this.vendedorNombreTienda = vendedorNombreTienda;
    }

    public String getVendedorDescripcion() {
        return vendedorDescripcion;
    }

    public void setVendedorDescripcion(String vendedorDescripcion) {
        this.vendedorDescripcion = vendedorDescripcion;
    }

    public String getVendedorLogoUrl() {
        return vendedorLogoUrl;
    }

    public void setVendedorLogoUrl(String vendedorLogoUrl) {
        this.vendedorLogoUrl = vendedorLogoUrl;
    }

    public Timestamp getVendedorFechaCreacion() {
        return vendedorFechaCreacion;
    }

    public void setVendedorFechaCreacion(Timestamp vendedorFechaCreacion) {
        this.vendedorFechaCreacion = vendedorFechaCreacion;
    }

    public String getVendedorEstado() {
        return vendedorEstado;
    }

    public void setVendedorEstado(String vendedorEstado) {
        this.vendedorEstado = vendedorEstado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "vendedorId=" + vendedorId +
                ", vendedorNombreTienda='" + vendedorNombreTienda + '\'' +
                ", vendedorDescripcion='" + vendedorDescripcion + '\'' +
                ", vendedorLogoUrl='" + vendedorLogoUrl + '\'' +
                ", vendedorFechaCreacion=" + vendedorFechaCreacion +
                ", vendedorEstado='" + vendedorEstado + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
