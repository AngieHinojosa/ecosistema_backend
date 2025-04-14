package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
import com.miprimerspring.nuestroecosistema.mapper.CarritoMapper;
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
    private final CarritoMapper carritoMapper;

    @Autowired
    public CarritoServiceImpl(CarritoRepository carritoRepository, CarritoMapper carritoMapper) {
        this.carritoRepository = carritoRepository;
        this.carritoMapper = carritoMapper;
    }

    @Override
    public CarritoDTO crearCarrito(CarritoDTO carritoDTO) {
        Carrito carrito = carritoMapper.toEntity(carritoDTO);
        Carrito savedCarrito = carritoRepository.save(carrito);
        return carritoMapper.toDTO(savedCarrito);
    }

    @Override
    public CarritoDTO obtenerCarritoPorId(Long id) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        return carritoMapper.toDTO(carrito);
    }

    @Override
    public List<CarritoDTO> obtenerCarritosPorUsuario(Long usuarioId) {
        List<Carrito> carritos = carritoRepository.findCarritosByUsuarioId(usuarioId);
        return carritos.stream()
                .map(carritoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarritoDTO> obtenerTodosCarritos() {
        List<Carrito> carritos = carritoRepository.findAll();
        return carritos.stream()
                .map(carritoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarritoDTO actualizarCarrito(Long id, CarritoDTO carritoDTO) {
        Carrito carritoExistente = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carritoExistente = carritoMapper.toEntity(carritoDTO);
        carritoExistente.setCarritoId(id);  // Mantener el ID
        Carrito updatedCarrito = carritoRepository.save(carritoExistente);
        return carritoMapper.toDTO(updatedCarrito);
    }

    @Override
    public void eliminarCarrito(Long id) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carritoRepository.delete(carrito);
    }
}