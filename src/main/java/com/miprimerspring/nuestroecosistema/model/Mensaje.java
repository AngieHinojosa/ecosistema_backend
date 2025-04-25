package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mensaje_id")
    private Long mensajeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emisor_id", nullable = false)
    private Usuario emisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario receptor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "soporte_id", nullable = false)
    private Usuario soporte;  // Nuevo campo para el soporte

    @Column(name = "mensaje_contenido", length = 255)
    private String mensajeContenido;

    @Column(name = "mensaje_leido", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean mensajeLeido;

    @Column(name = "mensaje_enviado_en", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp mensajeEnviadoEn;
}