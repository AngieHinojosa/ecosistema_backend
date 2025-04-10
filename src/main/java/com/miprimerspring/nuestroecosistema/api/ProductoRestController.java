package com.miprimerspring.nuestroecosistema.api;

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

    //Crear producto, obtener productos, actualizar producto, eliminar producto.
    @Autowired
    private ProductoService productoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> obtenerProductos(){
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id){
        return ResponseEntity.ok(productoService.obtenerProductoPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {
        return new ResponseEntity<>(productoService.crearProducto(nuevoProducto), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto nuevoProducto) {
        return ResponseEntity.ok(productoService.actualizarProducto(id,nuevoProducto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProductoPorId(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
