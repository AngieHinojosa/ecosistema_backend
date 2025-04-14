package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.CategoriaDTO;
import com.miprimerspring.nuestroecosistema.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    // Convertir Categoria a CategoriaDTO
    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        CategoriaDTO dto = new CategoriaDTO();
        dto.setCategoriaId(categoria.getCategoriaId());
        dto.setCategoriaNombre(categoria.getCategoriaNombre());
        dto.setCategoriaDescripcion(categoria.getCategoriaDescripcion());

        return dto;
    }

    // Convertir CategoriaDTO a Categoria
    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setCategoriaId(dto.getCategoriaId());
        categoria.setCategoriaNombre(dto.getCategoriaNombre());
        categoria.setCategoriaDescripcion(dto.getCategoriaDescripcion());

        return categoria;
    }
}