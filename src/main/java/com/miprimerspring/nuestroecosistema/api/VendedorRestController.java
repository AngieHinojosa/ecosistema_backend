package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.Vendedor;
import com.miprimerspring.nuestroecosistema.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorRestController {

    //Gestionar las tiendas y productos de los vendedores.
    //Crear vendedor, obtener vendedores, actualizar vendedor.
    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/lista")
    public ResponseEntity<List<Vendedor>> listar() {
        return ResponseEntity.ok(vendedorService.obtenerTodosLosVendedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(vendedorService.obtenerVendedorPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Vendedor> crear(@RequestBody Vendedor obj) {
        return new ResponseEntity<>(vendedorService.crearVendedor(obj), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Vendedor> actualizar(@PathVariable Long id, @RequestBody Vendedor obj) {
        return ResponseEntity.ok(vendedorService.actualizarVendedor(id, obj));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vendedorService.eliminarVendedor(id);
        return ResponseEntity.noContent().build();
    }
}
