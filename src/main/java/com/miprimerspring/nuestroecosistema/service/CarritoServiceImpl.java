package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.CarritoDTO;
import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.CarritoRepository;
import com.miprimerspring.nuestroecosistema.repository.ProductoRepository;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public CarritoServiceImpl(CarritoRepository carritoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Carrito crearCarritoDesdeDTO(CarritoDTO carritoDTO) {
        Usuario usuario = usuarioRepository.findById(carritoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(carritoDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setProducto(producto);
        carrito.setCantidad(carritoDTO.getCantidad());
        carrito.setAgregadoEn(LocalDateTime.now());

        return carritoRepository.save(carrito);
    }

    @Override
    public Optional<CarritoDTO> obtenerCarritoDTOPorId(Long carritoId) {
        Optional<Carrito> carrito = carritoRepository.findById(carritoId);
        return carrito.map(carrito1 -> new CarritoDTO());  // Usamos el constructor que acepta un Carrito
    }

    @Override
    public List<CarritoDTO> obtenerTodosLosCarritosDTO() {
        List<Carrito> carritos = carritoRepository.findAll();
        return carritos.stream()
                .map(this::convertirACarritoDTO) // Convierte cada carrito en un DTO
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCarrito(Long carritoId) {
        carritoRepository.deleteById(carritoId);
    }

    @Override
    public Carrito actualizarCarritoDesdeDTO(Long carritoId, CarritoDTO carritoDTO) {
        Carrito carritoExistente = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Usuario usuario = usuarioRepository.findById(carritoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(carritoDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        carritoExistente.setUsuario(usuario);
        carritoExistente.setProducto(producto);
        carritoExistente.setCantidad(carritoDTO.getCantidad());
        carritoExistente.setAgregadoEn(carritoDTO.getAgregadoEn());

        return carritoRepository.save(carritoExistente);
    }

    @Override
    public List<CarritoDTO> obtenerCarritosPorUsuario(Long usuarioId) {
        List<Carrito> carritos = carritoRepository.findByUsuarioUsuarioId(usuarioId);
        return carritos.stream()
                .map(this::convertirACarritoDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para convertir Carrito a CarritoDTO
    private CarritoDTO convertirACarritoDTO(Carrito carrito) {
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setCarritoId(carrito.getCarritoId());
        carritoDTO.setUsuarioId(carrito.getUsuario().getUsuarioId());
        carritoDTO.setProductoId(carrito.getProducto().getProductoId());
        carritoDTO.setCantidad(carrito.getCantidad());
        carritoDTO.setAgregadoEn(carrito.getAgregadoEn());
        return carritoDTO;
    }
}
