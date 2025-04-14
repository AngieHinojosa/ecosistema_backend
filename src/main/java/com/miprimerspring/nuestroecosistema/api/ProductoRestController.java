package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoRestController {

    private final ProductoService productoService;

    @Autowired
    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos
    @GetMapping("/todos")
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        List<ProductoDTO> productos = productoService.obtenerTodosProductos();
        return ResponseEntity.ok(productos);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable("id") Integer id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    // Obtener productos por vendedor
    @GetMapping("/vendedor/{vendedorId}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorVendedor(@PathVariable("vendedorId") Integer vendedorId) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorVendedor(vendedorId);
        return ResponseEntity.ok(productos);
    }

    // Obtener productos por categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorCategoria(@PathVariable("categoriaId") Integer categoriaId) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }

    // Obtener productos por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorEstado(@PathVariable("estado") String estado) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorEstado(estado);
        return ResponseEntity.ok(productos);
    }

    // Obtener productos por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorNombre(@PathVariable("nombre") String nombre) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    // Crear un nuevo producto
    @PostMapping("/nuevo")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO creado = productoService.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable("id") Integer id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO actualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Integer id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}