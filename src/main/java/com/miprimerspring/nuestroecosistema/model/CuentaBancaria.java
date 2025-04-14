package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Double cuentaSaldo;
}