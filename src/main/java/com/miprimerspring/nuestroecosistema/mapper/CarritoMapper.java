package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CarritoMapper {

    // Convertir Carrito a CarritoDTO
    public CarritoDTO toDTO(Carrito carrito) {
        if (carrito == null) {
            return null;
        }

        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setCarritoId(carrito.getCarritoId());
        carritoDTO.setUsuarioId(carrito.getUsuario() != null ? carrito.getUsuario().getUsuarioId() : null);
        carritoDTO.setProductoId(carrito.getProducto() != null ? carrito.getProducto().getProductoId() : null);
        carritoDTO.setCarritoCantidad(carrito.getCarritoCantidad());
        carritoDTO.setCarritoAgregadoEn(carrito.getCarritoAgregadoEn());
        carritoDTO.setCantidad(carrito.getCantidad());

        return carritoDTO;
    }

    // Convertir CarritoDTO a Carrito
    public Carrito toEntity(CarritoDTO carritoDTO) {
        if (carritoDTO == null) {
            return null;
        }

        Carrito carrito = new Carrito();
        carrito.setCarritoId(carritoDTO.getCarritoId());

        // Asignar el Usuario
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(carritoDTO.getUsuarioId());  // Solo asignamos el ID
        carrito.setUsuario(usuario);

        // Asignar el Producto
        Producto producto = new Producto();
        producto.setProductoId(carritoDTO.getProductoId());  // Solo asignamos el ID
        carrito.setProducto(producto);

        carrito.setCarritoCantidad(carritoDTO.getCarritoCantidad());
        carrito.setCarritoAgregadoEn(carritoDTO.getCarritoAgregadoEn());
        carrito.setCantidad(carritoDTO.getCantidad());

        return carrito;
    }
}