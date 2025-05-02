package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    @EqualsAndHashCode.Include
    private Long productoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

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

    @Column(name = "producto_img_url", length = 255)
    private String imgUrl;

    @CreationTimestamp
    @Column(name = "producto_creado_en", updatable = false)
    private Timestamp productoCreadoEn;
}
