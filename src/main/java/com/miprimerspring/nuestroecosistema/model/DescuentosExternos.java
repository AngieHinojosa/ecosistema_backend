package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "descuentos_externos")
public class DescuentosExternos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "descuento_id")
    private Integer descuentoId;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "descuento_id", nullable = false)
//    private DescuentosExternos descuentosExternos;

    @Column(name = "empresa_nombre", length = 255)
    private String empresaNombre;

    @Column(name = "descuento_porcentaje")
    private Double descuentoPorcentaje;

    @Column(name = "descuento_codigo", length = 255, unique = true)
    private String descuentoCodigo;

    @Column(name = "descuento_vigencia_inicio", columnDefinition = "VARBINARY(255)")
    private byte[] descuentoVigenciaInicio;

    @Column(name = "descuento_vigencia_fin", columnDefinition = "VARBINARY(255)")
    private byte[] descuentoVigenciaFin;

    @Column(name = "descuento_metodo_pago", length = 255)
    private String descuentoMetodoPago;

    @Column(name = "descuento_banco", length = 255)
    private String descuentoBanco;

    @Column(name = "descuento_activo", columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean descuentoActivo;
}