package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.RolDTO;
import com.miprimerspring.nuestroecosistema.model.Rol;
import com.miprimerspring.nuestroecosistema.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolRestController {

    private final RolService rolService;

    @Autowired
    public RolRestController(RolService rolService) {
        this.rolService = rolService;
    }

    // Obtener todos los roles
    @GetMapping("/todos")
    public ResponseEntity<List<RolDTO>> obtenerTodosRoles() {
        List<RolDTO> roles = rolService.obtenerTodosRoles();
        return ResponseEntity.ok(roles);
    }

    // Obtener un rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> obtenerRolPorId(@PathVariable("id") Integer id) {
        RolDTO rol = rolService.obtenerRolPorId(id);
        return rol != null ? ResponseEntity.ok(rol) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo rol
    @PostMapping("/nuevo")
    public ResponseEntity<RolDTO> crearRol(@RequestBody RolDTO rolDTO) {
        RolDTO creado = rolService.crearRol(rolDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar un rol
    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> actualizarRol(@PathVariable("id") Integer id, @RequestBody RolDTO rolDTO) {
        RolDTO actualizado = rolService.actualizarRol(id, rolDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable("id") Integer id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener rol por nombre
    @GetMapping("/nombre/{rolNombre}")
    public ResponseEntity<RolDTO> obtenerRolPorNombre(@PathVariable("rolNombre") String rolNombre) {
        RolDTO rol = rolService.obtenerRolPorNombre(rolNombre);
        return rol != null ? ResponseEntity.ok(rol) : ResponseEntity.notFound().build();
    }
}