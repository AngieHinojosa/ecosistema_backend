package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productoId;

    @Column(name = "producto_nombre", length = 100, nullable = false)
    private String productoNombre;

    @Column(name = "producto_descripcion", length = 255)
    private String productoDescripcion;

    @Column(name = "producto_precio", nullable = false)
    private double productoPrecio;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Producto() {
    }

    public Producto(long productoId, String productoNombre, String productoDescripcion, double productoPrecio, Categoria categoria) {
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.productoDescripcion = productoDescripcion;
        this.productoPrecio = productoPrecio;
        this.categoria = categoria;
    }

    public long getProductoId() {
        return productoId;
    }

    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    public void setProductoDescripcion(String productoDescripcion) {
        this.productoDescripcion = productoDescripcion;
    }

    public double getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(double productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "productoId=" + productoId +
                ", productoNombre='" + productoNombre + '\'' +
                ", productoDescripcion='" + productoDescripcion + '\'' +
                ", productoPrecio=" + productoPrecio +
                ", categoria=" + categoria +
                '}';
    }
}
