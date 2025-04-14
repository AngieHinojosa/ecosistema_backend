package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vendedores")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendedor_id")
    private Long vendedorId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "vendedor_nombre_tienda", length = 255)
    private String vendedorNombreTienda;

    @Column(name = "vendedor_descripcion", length = 255)
    private String vendedorDescripcion;

    @Column(name = "vendedor_logo_url", length = 255)
    private String vendedorLogoUrl;

    @Column(name = "vendedor_fecha_creacion", columnDefinition = "VARBINARY(255)")
    private String vendedorFechaCreacion;

    @Column(name = "vendedor_estado", length = 255)
    private String vendedorEstado;
}