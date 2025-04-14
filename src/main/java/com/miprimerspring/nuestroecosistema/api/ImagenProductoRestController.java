package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.ImagenProductoDTO;
import com.miprimerspring.nuestroecosistema.model.ImagenProducto;
import com.miprimerspring.nuestroecosistema.service.ImagenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes-productos")
public class ImagenProductoRestController {

    private final ImagenProductoService imagenProductoService;

    @Autowired
    public ImagenProductoRestController(ImagenProductoService imagenProductoService) {
        this.imagenProductoService = imagenProductoService;
    }

    @PostMapping("/nueva")
    public ResponseEntity<ImagenProductoDTO> crearImagenProducto(@RequestBody ImagenProductoDTO imagenProductoDTO) {
        ImagenProductoDTO createdImagenProducto = imagenProductoService.crearImagenProducto(imagenProductoDTO);
        return new ResponseEntity<>(createdImagenProducto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenProductoDTO> obtenerImagenProducto(@PathVariable Long id) {
        ImagenProductoDTO imagenProductoDTO = imagenProductoService.obtenerImagenProductoPorId(id);
        return new ResponseEntity<>(imagenProductoDTO, HttpStatus.OK);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ImagenProductoDTO>> obtenerImagenesPorProductoId(@PathVariable Integer productoId) {
        List<ImagenProductoDTO> imagenes = imagenProductoService.obtenerImagenesPorProductoId(productoId);
        return new ResponseEntity<>(imagenes, HttpStatus.OK);
    }

    @GetMapping("/principal")
    public ResponseEntity<List<ImagenProductoDTO>> obtenerImagenesPorPrincipal(@RequestParam Boolean imagenEsPrincipal) {
        List<ImagenProductoDTO> imagenes = imagenProductoService.obtenerImagenesPorPrincipal(imagenEsPrincipal);
        return new ResponseEntity<>(imagenes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImagenProductoDTO>> obtenerTodasImagenes() {
        List<ImagenProductoDTO> imagenes = imagenProductoService.obtenerTodasImagenes();
        return new ResponseEntity<>(imagenes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenProductoDTO> actualizarImagenProducto(@PathVariable Long id, @RequestBody ImagenProductoDTO imagenProductoDTO) {
        ImagenProductoDTO updatedImagenProducto = imagenProductoService.actualizarImagenProducto(id, imagenProductoDTO);
        return new ResponseEntity<>(updatedImagenProducto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagenProducto(@PathVariable Long id) {
        imagenProductoService.eliminarImagenProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}