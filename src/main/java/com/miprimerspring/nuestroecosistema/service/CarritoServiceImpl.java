package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.repository.CarritoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;

    @Autowired
    public CarritoServiceImpl(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    @Override
    public Carrito crearCarrito(Carrito carrito) {
        return carritoRepository.save(carrito); //Crear un nuevo carrito en la base de datos
    }

    @Override
    public Optional<Carrito> obtenerCarritoPorId(Long carritoId) {
        return carritoRepository.findById(carritoId); //Devuelve el carrito por su ID
    }

    @Override
    public List<Carrito> obtenerTodosLosCarritos() {
        return carritoRepository.findAll(); //Devuelve todos los carritos
    }

    @Override
    public void eliminarCarrito(Long carritoId) {
        carritoRepository.deleteById(carritoId); //Elimina el carrito por su ID

    }

    @Override
    public Carrito actualizarCarrito(Long carritoId, Carrito carrito) {
        //Primero verificamos si el carrito existe
        if (carritoRepository.existsById(carritoId)) {
            carrito.setCarritoId(carritoId); //Establecemos el ID para la actualización
            return carritoRepository.save(carrito); // Actualizamos el carrito
        }
        return null; // Si no existe el carrito, retornamos null
    }

    @Override
    public List<Carrito> obtenerCarritosPorUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId); //Consultamos los carritos de un usuario
    }
}
