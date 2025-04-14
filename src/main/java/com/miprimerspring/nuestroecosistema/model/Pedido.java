package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;  // Debería ser Long

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "direccion_id", nullable = false)
    private Direccion direccion;

    @Column(name = "pedido_total", precision = 14, scale = 2)
    private BigDecimal pedidoTotal;

    @Column(name = "pedido_moneda", length = 10)
    private String pedidoMoneda;

    @Column(name = "pedido_estado", length = 20)
    private String pedidoEstado;

    @Column(name = "pedido_metodo_pago", length = 50)
    private String pedidoMetodoPago;

    @Column(name = "pedido_uuid_pago", length = 36)
    private String pedidoUuidPago;

    @Column(name = "pedido_creado_en", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp pedidoCreadoEn;

    @Column(name = "pedido_fecha", nullable = false)
    private Date pedidoFecha;
}