package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.model.Categoria;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    // Convertir Producto a ProductoDTO
    public ProductoDTO toDTO(Producto entity) {
        if (entity == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();


        // Cambié Vendedor por Usuario
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getUsuarioId() : null);  // Usa usuarioId
        dto.setCategoriaId(entity.getCategoria() != null ? entity.getCategoria().getCategoriaId() : null);
        dto.setProductoNombre(entity.getProductoNombre());
        dto.setProductoDescripcion(entity.getProductoDescripcion());
        dto.setProductoPrecio(entity.getProductoPrecio());
        dto.setProductoStock(entity.getProductoStock());
        dto.setImgUrl(entity.getImgUrl());

        return dto;
    }

    // Convertir ProductoDTO a Producto
    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        Producto entity = new Producto();


        // Cambié Vendedor por Usuario
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(dto.getUsuarioId()); // Ahora usamos usuarioId en lugar de vendedorId
        entity.setUsuario(usuario); // Asignamos el Usuario al Producto

        Categoria categoria = new Categoria();
        categoria.setCategoriaId(dto.getCategoriaId());
        entity.setCategoria(categoria);

        entity.setProductoNombre(dto.getProductoNombre());
        entity.setProductoDescripcion(dto.getProductoDescripcion());
        entity.setProductoPrecio(dto.getProductoPrecio());
        entity.setProductoStock(dto.getProductoStock());
        entity.setImgUrl(dto.getImgUrl());

        return entity;
    }
}