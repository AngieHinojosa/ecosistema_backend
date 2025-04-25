package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios_descuentos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "descuento_id"})
})
public class UsuarioDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_descuento_id")
    private Long usuarioDescuentoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "descuento_aplicado_id")
    private DescuentoAplicado descuentoAplicado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "descuento_externo_id")
    private DescuentosExternos descuentosExternos;
}