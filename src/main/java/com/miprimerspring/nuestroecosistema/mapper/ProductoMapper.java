package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.model.Categoria;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.model.Vendedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto entity) {
        if (entity == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
        dto.setProductoId(entity.getProductoId());
        dto.setVendedorId(entity.getVendedor() != null ? entity.getVendedor().getVendedorId() : null);
        dto.setCategoriaId(entity.getCategoria() != null ? entity.getCategoria().getCategoriaId() : null);
        dto.setProductoNombre(entity.getProductoNombre());
        dto.setProductoDescripcion(entity.getProductoDescripcion());
        dto.setProductoPrecio(entity.getProductoPrecio());
        dto.setProductoStock(entity.getProductoStock());
        dto.setProductoEstado(entity.getProductoEstado());
        dto.setProductoCreadoEn(entity.getProductoCreadoEn());

        return dto;
    }

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        Producto entity = new Producto();
        entity.setProductoId(dto.getProductoId());

        Vendedor vendedor = new Vendedor();
        vendedor.setVendedorId(dto.getVendedorId());
        entity.setVendedor(vendedor);

        Categoria categoria = new Categoria();
        categoria.setCategoriaId(dto.getCategoriaId());
        entity.setCategoria(categoria);

        entity.setProductoNombre(dto.getProductoNombre());
        entity.setProductoDescripcion(dto.getProductoDescripcion());
        entity.setProductoPrecio(dto.getProductoPrecio());
        entity.setProductoStock(dto.getProductoStock());
        entity.setProductoEstado(dto.getProductoEstado());
        entity.setProductoCreadoEn(dto.getProductoCreadoEn());

        return entity;
    }
}