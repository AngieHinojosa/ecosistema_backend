package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.ImagenProducto;
import com.miprimerspring.nuestroecosistema.service.ImagenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagen-producto")
public class ImagenProductoRestController {

    @Autowired
    private ImagenProductoService imagenProductoService;

    @GetMapping("/lista")
    public ResponseEntity<List<ImagenProducto>> listar() {
        return ResponseEntity.ok(imagenProductoService.obtenerTodasLasImagenes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenProducto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(imagenProductoService.obtenerImagenPorId(id));
    }

    @PostMapping("/nueva")
    public ResponseEntity<ImagenProducto> crear(@RequestBody ImagenProducto obj) {
        return new ResponseEntity<>(imagenProductoService.crearImagenProducto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ImagenProducto> actualizar(@PathVariable Long id, @RequestBody ImagenProducto obj) {
        return ResponseEntity.ok(imagenProductoService.actualizarImagenProducto(id, obj));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        imagenProductoService.eliminarImagenProducto(id);
        return ResponseEntity.noContent().build();
    }
}
