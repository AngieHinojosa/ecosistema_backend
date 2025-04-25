package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"pedido"})
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id")
    private Long pagoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_id", nullable = true)
    private CuentaBancaria cuenta;

    @Column(name = "pago_monto")
    private Double pagoMonto;

    @Column(name = "pago_metodo", length = 255)
    private String pagoMetodo;

    @Column(name = "pago_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp pagoFecha;

    // 👇 Puedes añadir este método para depurar rápido en consola
    public void imprimirDatosPago() {
        System.out.println("=== DATOS DEL PAGO ===");
        System.out.println("ID: " + pagoId);
        System.out.println("Método: " + pagoMetodo);
        System.out.println("Monto: " + pagoMonto);
        System.out.println("Fecha: " + pagoFecha);
        if (cuenta != null) {
            System.out.println("Cuenta asociada: " + cuenta.getCuentaNumero());
        } else {
            System.out.println("Cuenta asociada: Ninguna");
        }
        System.out.println("======================");
    }
}


