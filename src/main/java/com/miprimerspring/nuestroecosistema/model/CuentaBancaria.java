package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"usuario"})
@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long cuentaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "cuenta_tipo", nullable = false, length = 255)
    private String cuentaTipo;

    @Column(name = "cuenta_numero", nullable = false, unique = true, length = 255)
    private String cuentaNumero;

    @Column(name = "cuenta_saldo", nullable = false)
    private BigDecimal cuentaSaldo;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        if (cuentaTipo == null) {
            cuentaTipo = "DEBITO";
        }
        if (cuentaNumero == null) {
            cuentaNumero = generarNumeroCuenta();
        }
        if (cuentaSaldo == null) {
            cuentaSaldo = BigDecimal.ZERO;
        }
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }

        System.out.println("CuentaBancaria prePersist ejecutado:");
        System.out.println(this);
    }

    private String generarNumeroCuenta() {
        // Genera un número de 16 dígitos aleatorio
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }
}