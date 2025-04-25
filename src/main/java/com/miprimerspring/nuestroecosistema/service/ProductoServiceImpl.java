package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.mapper.ProductoMapper;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.ProductoRepository;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import com.miprimerspring.nuestroecosistema.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository,
                               ProductoMapper productoMapper,
                               UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        // Obtener el usuario autenticado desde UserDetailsImpl
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long usuarioId = userDetails.getUsuarioId();  // Obtenemos el usuarioId desde UserDetailsImpl

        // Buscar el Usuario en la base de datos usando el usuarioId
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar si el usuario tiene el rol de vendedor
        if (!usuario.getRoles().contains(ERol.ROLE_VENDEDOR)) {
            throw new RuntimeException("El usuario no tiene rol de vendedor.");
        }

        // Convertir el DTO a la entidad Producto
        Producto producto = productoMapper.toEntity(productoDTO);

        // Asociar el producto con el usuario (ya que el usuario es ahora el "vendedor")
        producto.setUsuario(usuario);

        // Guardar el producto en la base de datos
        producto = productoRepository.save(producto);

        // Convertir el producto guardado a DTO y devolverlo
        return productoMapper.toDTO(producto);
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Integer id) {
        return productoRepository.findById(Long.valueOf(id))
                .map(productoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorCategoria(Integer categoriaId) {
        List<Producto> productos = productoRepository.findByCategoria_CategoriaId(categoriaId);
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre) {
        List<Producto> productos = productoRepository.findByProductoNombreContaining(nombre);
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerTodosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO actualizarProducto(Integer id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        // Actualizar los campos del producto
        producto.setProductoNombre(productoDTO.getProductoNombre());
        producto.setProductoDescripcion(productoDTO.getProductoDescripcion());
        producto.setProductoPrecio(productoDTO.getProductoPrecio());

        // Guardar cambios
        producto = productoRepository.save(producto);
        return productoMapper.toDTO(producto);
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorUsuario(Long usuarioId) {
        List<Producto> productos = productoRepository.findByUsuario_UsuarioId(usuarioId); // Repositorio consulta
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(Long.valueOf(id));
    }
}