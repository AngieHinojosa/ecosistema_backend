package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "puntos_fidelizacion")
public class PuntosFidelizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "punto_id")
    private Long puntoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "punto_cantidad")
    private Integer puntoCantidad;

    @Column(name = "punto_ultima_actualizacion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp puntoUltimaActualizacion;

    // Métodos para acceder a los puntos acumulados
    public Integer getPuntosAcumulados() {
        return puntoCantidad;
    }

    public void setPuntosAcumulados(Integer puntosAcumulados) {
        this.puntoCantidad = puntosAcumulados;
    }
}