package com.miprimerspring.nuestroecosistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long categoriaId;

    @Column(name = "categoria_nombre", nullable = false, unique = true, length = 50)
    private String categoriaNombre;

    @Column(name = "categoria_descripcion", columnDefinition = "TEXT")
    private String categoriaDescripcion;

    // Relación uno a muchos con Producto
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    private Set<Producto> productos;  // Usamos Set para evitar duplicados
}