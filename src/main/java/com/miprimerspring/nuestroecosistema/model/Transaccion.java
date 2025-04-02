package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transaccionId;

    @Column(name = "transaccion_origen", nullable = false)
    private long transaccionOrigen;

    @Column(name = "transaccion_destino", nullable = false)
    private long transaccionDestino;

    @Column(name = "transaccion_monto", nullable = false)
    private double transaccionMonto;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaBancaria cuentaBancaria;

    public Transaccion() {
    }

    public Transaccion(long transaccionId, long transaccionOrigen, long transaccionDestino, double transaccionMonto, CuentaBancaria cuentaBancaria) {
        this.transaccionId = transaccionId;
        this.transaccionOrigen = transaccionOrigen;
        this.transaccionDestino = transaccionDestino;
        this.transaccionMonto = transaccionMonto;
        this.cuentaBancaria = cuentaBancaria;
    }

    public long getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(long transaccionId) {
        this.transaccionId = transaccionId;
    }

    public long getTransaccionOrigen() {
        return transaccionOrigen;
    }

    public void setTransaccionOrigen(long transaccionOrigen) {
        this.transaccionOrigen = transaccionOrigen;
    }

    public long getTransaccionDestino() {
        return transaccionDestino;
    }

    public void setTransaccionDestino(long transaccionDestino) {
        this.transaccionDestino = transaccionDestino;
    }

    public double getTransaccionMonto() {
        return transaccionMonto;
    }

    public void setTransaccionMonto(double transaccionMonto) {
        this.transaccionMonto = transaccionMonto;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "transaccionId=" + transaccionId +
                ", transaccionOrigen=" + transaccionOrigen +
                ", transaccionDestino=" + transaccionDestino +
                ", transaccionMonto=" + transaccionMonto +
                ", cuentaBancaria=" + cuentaBancaria +
                '}';
    }
}
