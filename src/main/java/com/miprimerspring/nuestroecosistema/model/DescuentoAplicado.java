package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "descuentos_aplicados")
public class DescuentoAplicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "descuento_aplicado_id")
    private Long descuentoAplicadoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "descuento_id", nullable = false)
    private DescuentosExternos descuentosExternos;

    @Column(name = "descuento_monto")
    private Double descuentoMonto;

    @Column(name = "descuento_codigo", length = 255)
    private String descuentoCodigo;
}