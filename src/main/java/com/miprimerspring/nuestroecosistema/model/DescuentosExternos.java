package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "descuentos_externos")
public class DescuentosExternos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "descuento_externo_id")
    private Long descuentoExternoId;

    @Column(name = "empresa", length = 255, nullable = false)
    private String empresa;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "porcentaje_descuento", nullable = false)
    private Double porcentajeDescuento;

    @Column(name = "codigo_descuento", length = 50)
    private String codigoDescuento;
}