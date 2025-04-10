package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.DTO.CategoriaDTO;
import com.miprimerspring.nuestroecosistema.model.Categoria;
import com.miprimerspring.nuestroecosistema.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaRestController {

    //Gestionar las categorías de productos.
    //Crear categoría, obtener categorías, actualizar categoría.

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaDTO>> obtenerCategorias() {
        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
        // Convertir cada categoría a DTO
        List<CategoriaDTO> categoriaDTOs = categorias.stream()
                .map(categoriaService::convertirACategoriaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoriaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoria(@PathVariable Long id) {
        Optional<Categoria> categoriaOpt = categoriaService.obtenerCategoriaPorId(id);
        if (categoriaOpt.isPresent()) {
            CategoriaDTO categoriaDTO = categoriaService.convertirACategoriaDTO(categoriaOpt.get());
            return ResponseEntity.ok(categoriaDTO);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra la categoría
        }
    }

    @PostMapping("/nueva")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content después de eliminar
    }
}