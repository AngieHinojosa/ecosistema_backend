package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.CategoriaDTO;
import com.miprimerspring.nuestroecosistema.model.Categoria;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con el id: " + id);
        }
        categoria.setCategoriaId(id);  // Aseguramos que se actualice el ID de la categoría
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con el id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaDTO convertirACategoriaDTO(Categoria categoria) {
        // Convertimos la lista de productos a una lista de IDs
        List<Long> productoIds = categoria.getProductos().stream()
                .map(Producto::getProductoId) // Solo obtenemos los IDs de los productos
                .collect(Collectors.toList());

        // Creamos y devolvemos el DTO
        return new CategoriaDTO(categoria.getCategoriaId(), categoria.getCategoriaNombre(), productoIds);
    }

}
