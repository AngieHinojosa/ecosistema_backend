package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cuentaId;

    @Column(name = "cuenta_tipo", nullable = false)
    private String cuentaTipo;

    @Column(name = "cuenta_numero", nullable = false)
    private String cuentaNumero;

    @Column(name = "cuenta_saldo", nullable = false)
    private double cuentaSaldo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public CuentaBancaria() {
    }

    public CuentaBancaria(long cuentaId, String cuentaTipo, String cuentaNumero, double cuentaSaldo, Usuario usuario) {
        this.cuentaId = cuentaId;
        this.cuentaTipo = cuentaTipo;
        this.cuentaNumero = cuentaNumero;
        this.cuentaSaldo = cuentaSaldo;
        this.usuario = usuario;
    }

    public long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getCuentaTipo() {
        return cuentaTipo;
    }

    public void setCuentaTipo(String cuentaTipo) {
        this.cuentaTipo = cuentaTipo;
    }

    public String getCuentaNumero() {
        return cuentaNumero;
    }

    public void setCuentaNumero(String cuentaNumero) {
        this.cuentaNumero = cuentaNumero;
    }

    public double getCuentaSaldo() {
        return cuentaSaldo;
    }

    public void setCuentaSaldo(double cuentaSaldo) {
        this.cuentaSaldo = cuentaSaldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "cuentaId=" + cuentaId +
                ", cuentaTipo='" + cuentaTipo + '\'' +
                ", cuentaNumero='" + cuentaNumero + '\'' +
                ", cuentaSaldo=" + cuentaSaldo +
                ", usuario=" + usuario +
                '}';
    }
}
