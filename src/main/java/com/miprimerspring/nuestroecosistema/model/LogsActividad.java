package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "logs_actividad")
public class LogsActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "log_accion", nullable = false, length = 255)
    private String logAccion;

    @Column(name = "log_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp logFecha;

    @Column(name = "log_detalles", columnDefinition = "JSON")
    private String logDetalles;
}