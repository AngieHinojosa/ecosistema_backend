package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes_producto")
public class ImagenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imagenId;

    private String imagenUrl;
    private Boolean imagenEsPrincipal;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public ImagenProducto() {
    }

    public ImagenProducto(long imagenId, String imagenUrl, Boolean imagenEsPrincipal, Producto producto) {
        this.imagenId = imagenId;
        this.imagenUrl = imagenUrl;
        this.imagenEsPrincipal = imagenEsPrincipal;
        this.producto = producto;
    }

    public long getImagenId() {
        return imagenId;
    }

    public void setImagenId(long imagenId) {
        this.imagenId = imagenId;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Boolean getImagenEsPrincipal() {
        return imagenEsPrincipal;
    }

    public void setImagenEsPrincipal(Boolean imagenEsPrincipal) {
        this.imagenEsPrincipal = imagenEsPrincipal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "ImagenProducto{" +
                "imagenId=" + imagenId +
                ", imagenUrl='" + imagenUrl + '\'' +
                ", imagenEsPrincipal=" + imagenEsPrincipal +
                ", producto=" + producto +
                '}';
    }
}
