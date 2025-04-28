package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.ImagenProductoDTO;
import com.miprimerspring.nuestroecosistema.service.ImagenProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes-productos")
@Tag(name = "ImagenProducto", description = "Operaciones relacionadas con las imágenes de productos")
public class ImagenProductoRestController {

    private final ImagenProductoService imagenProductoService;

    @Autowired
    public ImagenProductoRestController(ImagenProductoService imagenProductoService) {
        this.imagenProductoService = imagenProductoService;
    }

    @Operation(summary = "Crear una nueva imagen para un producto", tags = {"ImagenProducto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagen de producto creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImagenProductoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados")
    })
    @PostMapping("/nueva")
    public ResponseEntity<ImagenProductoDTO> crearImagenProducto(@RequestBody ImagenProductoDTO imagenProductoDTO) {
        ImagenProductoDTO createdImagenProducto = imagenProductoService.crearImagenProducto(imagenProductoDTO);
        return new ResponseEntity<>(createdImagenProducto, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener imagen de producto por ID", tags = {"ImagenProducto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen de producto encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImagenProductoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Imagen de producto no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ImagenProductoDTO> obtenerImagenProducto(@PathVariable Long id) {
        ImagenProductoDTO imagenProductoDTO = imagenProductoService.obtenerImagenProductoPorId(id);
        return new ResponseEntity<>(imagenProductoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtener imágenes por ID de producto", tags = {"ImagenProducto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imágenes de producto obtenidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron imágenes para este producto")
    })
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ImagenProductoDTO>> obtenerImagenesPorProductoId(@PathVariable Integer productoId) {
        List<ImagenProductoDTO> imagenes = imagenProductoService.obtenerImagenesPorProductoId(productoId);
        return new ResponseEntity<>(imagenes, HttpStatus.OK);
    }

    @Operation(summary = "Obtener imágenes principales de productos", tags = {"ImagenProducto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imágenes principales obtenidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/principal")
    public ResponseEntity<List<ImagenProductoDTO>> obtenerImagenesPorPrincipal(@RequestParam Boolean imagenEsPrincipal) {
        List<ImagenProductoDTO> imagenes = imagenProductoService.obtenerImagenesPorPrincipal(imagenEsPrincipal);
        return new ResponseEntity<>(imagenes, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las imágenes de productos", tags = {"ImagenProducto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas las imágenes obtenidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping
    public ResponseEntity<List<ImagenProductoDTO>> obtenerTodasImagenes() {
        List<ImagenProductoDTO> imagenes = imagenProductoService.obtenerTodasImagenes();
        return new ResponseEntity<>(imagenes, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar una imagen de producto", tags = {"ImagenProducto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen de producto actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImagenProductoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Imagen de producto no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ImagenProductoDTO> actualizarImagenProducto(@PathVariable Long id, @RequestBody ImagenProductoDTO imagenProductoDTO) {
        ImagenProductoDTO updatedImagenProducto = imagenProductoService.actualizarImagenProducto(id, imagenProductoDTO);
        return new ResponseEntity<>(updatedImagenProducto, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una imagen de producto", tags = {"ImagenProducto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagen de producto eliminada"),
            @ApiResponse(responseCode = "404", description = "Imagen de producto no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagenProducto(@PathVariable Long id) {
        imagenProductoService.eliminarImagenProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
