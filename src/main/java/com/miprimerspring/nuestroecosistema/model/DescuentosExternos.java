package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "descuentos_externos")
public class DescuentosExternos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long descuentoId;

    private String empresaNombre;
    private Double descuentoPorcentaje;
    private String descuentoCodigo;
    private Timestamp descuentoVigenciaInicio;
    private Timestamp descuentoVigenciaFin;
    private String descuentoMetodoPago;
    private String descuentoBanco;
    private Boolean descuentoActivo;

    public DescuentosExternos() {
    }

    public DescuentosExternos(long descuentoId, String empresaNombre, Double descuentoPorcentaje, String descuentoCodigo, Timestamp descuentoVigenciaInicio, Timestamp descuentoVigenciaFin, String descuentoMetodoPago, String descuentoBanco, Boolean descuentoActivo) {
        this.descuentoId = descuentoId;
        this.empresaNombre = empresaNombre;
        this.descuentoPorcentaje = descuentoPorcentaje;
        this.descuentoCodigo = descuentoCodigo;
        this.descuentoVigenciaInicio = descuentoVigenciaInicio;
        this.descuentoVigenciaFin = descuentoVigenciaFin;
        this.descuentoMetodoPago = descuentoMetodoPago;
        this.descuentoBanco = descuentoBanco;
        this.descuentoActivo = descuentoActivo;
    }

    public long getDescuentoId() {
        return descuentoId;
    }

    public void setDescuentoId(long descuentoId) {
        this.descuentoId = descuentoId;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public Double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(Double descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public String getDescuentoCodigo() {
        return descuentoCodigo;
    }

    public void setDescuentoCodigo(String descuentoCodigo) {
        this.descuentoCodigo = descuentoCodigo;
    }

    public Timestamp getDescuentoVigenciaInicio() {
        return descuentoVigenciaInicio;
    }

    public void setDescuentoVigenciaInicio(Timestamp descuentoVigenciaInicio) {
        this.descuentoVigenciaInicio = descuentoVigenciaInicio;
    }

    public Timestamp getDescuentoVigenciaFin() {
        return descuentoVigenciaFin;
    }

    public void setDescuentoVigenciaFin(Timestamp descuentoVigenciaFin) {
        this.descuentoVigenciaFin = descuentoVigenciaFin;
    }

    public String getDescuentoMetodoPago() {
        return descuentoMetodoPago;
    }

    public void setDescuentoMetodoPago(String descuentoMetodoPago) {
        this.descuentoMetodoPago = descuentoMetodoPago;
    }

    public String getDescuentoBanco() {
        return descuentoBanco;
    }

    public void setDescuentoBanco(String descuentoBanco) {
        this.descuentoBanco = descuentoBanco;
    }

    public Boolean getDescuentoActivo() {
        return descuentoActivo;
    }

    public void setDescuentoActivo(Boolean descuentoActivo) {
        this.descuentoActivo = descuentoActivo;
    }

    @Override
    public String toString() {
        return "DescuentosExternos{" +
                "descuentoId=" + descuentoId +
                ", empresaNombre='" + empresaNombre + '\'' +
                ", descuentoPorcentaje=" + descuentoPorcentaje +
                ", descuentoCodigo='" + descuentoCodigo + '\'' +
                ", descuentoVigenciaInicio=" + descuentoVigenciaInicio +
                ", descuentoVigenciaFin=" + descuentoVigenciaFin +
                ", descuentoMetodoPago='" + descuentoMetodoPago + '\'' +
                ", descuentoBanco='" + descuentoBanco + '\'' +
                ", descuentoActivo=" + descuentoActivo +
                '}';
    }
}
