package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_descuentos")
public class UsuarioDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usuarioDescuentoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "descuento_id", nullable = false)
    private DescuentosExternos descuento;

    public UsuarioDescuento() {
    }

    public UsuarioDescuento(long usuarioDescuentoId, Usuario usuario, DescuentosExternos descuento) {
        this.usuarioDescuentoId = usuarioDescuentoId;
        this.usuario = usuario;
        this.descuento = descuento;
    }

    public long getUsuarioDescuentoId() {
        return usuarioDescuentoId;
    }

    public void setUsuarioDescuentoId(long usuarioDescuentoId) {
        this.usuarioDescuentoId = usuarioDescuentoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DescuentosExternos getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentosExternos descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "UsuarioDescuento{" +
                "usuarioDescuentoId=" + usuarioDescuentoId +
                ", usuario=" + usuario +
                ", descuento=" + descuento +
                '}';
    }
}
