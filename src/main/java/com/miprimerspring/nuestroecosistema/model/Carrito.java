package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "carritos_compras")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carrito_id")
    private long carritoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "carrito_cantidad", nullable = false)
    private int cantidad;

    @Column(name = "carrito_agregado_en", nullable = false)
    private LocalDateTime agregadoEn;

    public Carrito() {
    }

    public Carrito(Long carritoId, Usuario usuario, Producto producto, int cantidad, LocalDateTime agregadoEn) {
        this.carritoId = carritoId;
        this.usuario = usuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.agregadoEn = agregadoEn;
    }

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getAgregadoEn() {
        return agregadoEn;
    }

    public void setAgregadoEn(LocalDateTime agregadoEn) {
        this.agregadoEn = agregadoEn;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "carritoId=" + carritoId +
                ", usuario=" + usuario +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", agregadoEn=" + agregadoEn +
                '}';
    }
}

