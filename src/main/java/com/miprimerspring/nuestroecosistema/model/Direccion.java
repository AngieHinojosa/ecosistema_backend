package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direccion_id")
    private Long direccionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "direccion_calle", length = 255)
    private String direccionCalle;

    @Column(name = "direccion_numero", length = 255)
    private String direccionNumero;

    @Column(name = "direccion_comuna", length = 255)
    private String direccionComuna;

    @Column(name = "direccion_ciudad", length = 255)
    private String direccionCiudad;

    @Column(name = "direccion_region", length = 255)
    private String direccionRegion;

    @Column(name = "direccion_codigo_postal", length = 255)
    private String direccionCodigoPostal;

    @Column(name = "direccion_pais", length = 255)
    private String direccionPais;

}