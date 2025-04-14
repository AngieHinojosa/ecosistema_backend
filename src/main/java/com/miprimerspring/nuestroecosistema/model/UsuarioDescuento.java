package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios_descuentos")
public class UsuarioDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_descuento_id")
    private Long usuarioDescuentoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "descuento_id", nullable = false)
    private DescuentosExternos descuento;

    @Column(name = "descuento_aplicado", nullable = false)
    private Boolean descuentoAplicado;  // Añadido el campo descuentoAplicado
}