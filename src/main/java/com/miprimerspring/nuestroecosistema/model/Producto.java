package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(name = "producto_nombre", length = 100, nullable = false)
    private String productoNombre;

    @Column(name = "producto_descripcion", length = 255)
    private String productoDescripcion;

    @Column(name = "producto_precio", nullable = false)
    private Double productoPrecio;

    @Column(name = "producto_stock")
    private Integer productoStock;

    @Column(name = "producto_estado", length = 20)
    private String productoEstado;

    @Column(name = "producto_creado_en", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp productoCreadoEn;
}