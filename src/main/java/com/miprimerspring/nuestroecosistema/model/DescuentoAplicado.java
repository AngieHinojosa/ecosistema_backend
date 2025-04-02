package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "descuentos_aplicados")
public class DescuentoAplicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long descuentoAplicadoId;

    private Double descuentoMonto;
    private String descuentoCodigo;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "descuento_id", nullable = false)
    private DescuentosExternos descuento;

    public DescuentoAplicado() {
    }

    public DescuentoAplicado(long descuentoAplicadoId, Double descuentoMonto, String descuentoCodigo, Pedido pedido, DescuentosExternos descuento) {
        this.descuentoAplicadoId = descuentoAplicadoId;
        this.descuentoMonto = descuentoMonto;
        this.descuentoCodigo = descuentoCodigo;
        this.pedido = pedido;
        this.descuento = descuento;
    }

    public long getDescuentoAplicadoId() {
        return descuentoAplicadoId;
    }

    public void setDescuentoAplicadoId(long descuentoAplicadoId) {
        this.descuentoAplicadoId = descuentoAplicadoId;
    }

    public Double getDescuentoMonto() {
        return descuentoMonto;
    }

    public void setDescuentoMonto(Double descuentoMonto) {
        this.descuentoMonto = descuentoMonto;
    }

    public String getDescuentoCodigo() {
        return descuentoCodigo;
    }

    public void setDescuentoCodigo(String descuentoCodigo) {
        this.descuentoCodigo = descuentoCodigo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public DescuentosExternos getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentosExternos descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "DescuentoAplicado{" +
                "descuentoAplicadoId=" + descuentoAplicadoId +
                ", descuentoMonto=" + descuentoMonto +
                ", descuentoCodigo='" + descuentoCodigo + '\'' +
                ", pedido=" + pedido +
                ", descuento=" + descuento +
                '}';
    }
}
