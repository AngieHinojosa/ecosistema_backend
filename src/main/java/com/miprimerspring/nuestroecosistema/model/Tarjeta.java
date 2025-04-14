package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjetas")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tarjeta_id")
    private Long tarjetaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "tarjeta_tipo", length = 50)
    private String tarjetaTipo;

    @Column(name = "tarjeta_numero", length = 255, unique = true)
    private String tarjetaNumero;

    @Column(name = "tarjeta_expiracion", length = 5) // Ejemplo: 12/23
    private String tarjetaExpiracion;

    @Column(name = "tarjeta_cvv", length = 3)
    private String tarjetaCvv;

    @Column(name = "tarjeta_estado", length = 20)
    private String tarjetaEstado;
}

