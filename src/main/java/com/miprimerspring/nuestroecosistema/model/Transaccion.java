package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaccion_id")
    private Long transaccionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaBancaria cuenta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaccion_origen", nullable = false)
    private CuentaBancaria transaccionOrigen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaccion_destino", nullable = false)
    private CuentaBancaria transaccionDestino;

    @Column(name = "transaccion_monto", nullable = false)
    private Double transaccionMonto;

    @Column(name = "transaccion_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp transaccionFecha;
}