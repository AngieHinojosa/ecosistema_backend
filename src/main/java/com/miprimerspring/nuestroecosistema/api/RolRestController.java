package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.model.Rol;
import com.miprimerspring.nuestroecosistema.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolRestController {

    //Metodos para manejar roles: por ejemplo, administrador, cliente, vendedor.
    //Operaciones típicas: Crear rol, obtener roles, actualizar rol, eliminar rol.
    @Autowired
    private RolService rolService;

    @GetMapping("/lista")
    public ResponseEntity<List<Rol>> listar() {
        return ResponseEntity.ok(rolService.obtenerTodosLosRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.obtenerRolPorId(id));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Rol> crear(@RequestBody Rol obj) {
        return new ResponseEntity<>(rolService.crearRol(obj), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Long id, @RequestBody Rol obj) {
        return ResponseEntity.ok(rolService.actualizarRol(id, obj));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}
