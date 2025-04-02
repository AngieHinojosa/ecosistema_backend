package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_detalle")
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long detalleId;

    private Double detallePrecioUnitario;
    private Integer detalleCantidad;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public PedidoDetalle() {
    }

    public PedidoDetalle(long detalleId, Double detallePrecioUnitario, Integer detalleCantidad, Pedido pedido, Producto producto) {
        this.detalleId = detalleId;
        this.detallePrecioUnitario = detallePrecioUnitario;
        this.detalleCantidad = detalleCantidad;
        this.pedido = pedido;
        this.producto = producto;
    }

    public long getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(long detalleId) {
        this.detalleId = detalleId;
    }

    public Double getDetallePrecioUnitario() {
        return detallePrecioUnitario;
    }

    public void setDetallePrecioUnitario(Double detallePrecioUnitario) {
        this.detallePrecioUnitario = detallePrecioUnitario;
    }

    public Integer getDetalleCantidad() {
        return detalleCantidad;
    }

    public void setDetalleCantidad(Integer detalleCantidad) {
        this.detalleCantidad = detalleCantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "PedidoDetalle{" +
                "detalleId=" + detalleId +
                ", detallePrecioUnitario=" + detallePrecioUnitario +
                ", detalleCantidad=" + detalleCantidad +
                ", pedido=" + pedido +
                ", producto=" + producto +
                '}';
    }
}