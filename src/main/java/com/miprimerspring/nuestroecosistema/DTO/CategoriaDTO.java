package com.miprimerspring.nuestroecosistema.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CategoriaDTO {

    private long categoriaId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String categoriaNombre;

    private List<Long> productoIds; // Lista de IDs de productos asociados

    public CategoriaDTO(long categoriaId, String categoriaNombre, List<Long> productoIds) {
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.productoIds = productoIds;
    }
}

