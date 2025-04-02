package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pedidoId;

    @Column(name = "pedido_fecha", nullable = false)
    private Date pedidoFecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoDetalle> productos;

    public Pedido() {
    }

    public Pedido(long pedidoId, Date pedidoFecha, Usuario usuario, List<PedidoDetalle> productos) {
        this.pedidoId = pedidoId;
        this.pedidoFecha = pedidoFecha;
        this.usuario = usuario;
        this.productos = productos;
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Date getPedidoFecha() {
        return pedidoFecha;
    }

    public void setPedidoFecha(Date pedidoFecha) {
        this.pedidoFecha = pedidoFecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PedidoDetalle> getProductos() {
        return productos;
    }

    public void setProductos(List<PedidoDetalle> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "pedidoId=" + pedidoId +
                ", pedidoFecha=" + pedidoFecha +
                ", usuario=" + usuario +
                ", productos=" + productos +
                '}';
    }
}
