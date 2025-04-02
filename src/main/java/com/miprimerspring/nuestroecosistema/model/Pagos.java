package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pagoId;

    private Double pagoMonto;
    private String pagoMetodo;
    private Timestamp pagoFecha;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaBancaria cuenta;

    public Pagos() {
    }

    public Pagos(long pagoId, Double pagoMonto, String pagoMetodo, Timestamp pagoFecha, Pedido pedido, CuentaBancaria cuenta) {
        this.pagoId = pagoId;
        this.pagoMonto = pagoMonto;
        this.pagoMetodo = pagoMetodo;
        this.pagoFecha = pagoFecha;
        this.pedido = pedido;
        this.cuenta = cuenta;
    }

    public long getPagoId() {
        return pagoId;
    }

    public void setPagoId(long pagoId) {
        this.pagoId = pagoId;
    }

    public Double getPagoMonto() {
        return pagoMonto;
    }

    public void setPagoMonto(Double pagoMonto) {
        this.pagoMonto = pagoMonto;
    }

    public String getPagoMetodo() {
        return pagoMetodo;
    }

    public void setPagoMetodo(String pagoMetodo) {
        this.pagoMetodo = pagoMetodo;
    }

    public Timestamp getPagoFecha() {
        return pagoFecha;
    }

    public void setPagoFecha(Timestamp pagoFecha) {
        this.pagoFecha = pagoFecha;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Pagos{" +
                "pagoId=" + pagoId +
                ", pagoMonto=" + pagoMonto +
                ", pagoMetodo='" + pagoMetodo + '\'' +
                ", pagoFecha=" + pagoFecha +
                ", pedido=" + pedido +
                ", cuenta=" + cuenta +
                '}';
    }
}
