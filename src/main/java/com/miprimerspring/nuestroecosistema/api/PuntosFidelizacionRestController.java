package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.PuntosFidelizacionDTO;
import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;
import com.miprimerspring.nuestroecosistema.service.PuntosFidelizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntos_fidelizacion")
public class PuntosFidelizacionRestController {

    private final PuntosFidelizacionService puntosFidelizacionService;

    @Autowired
    public PuntosFidelizacionRestController(PuntosFidelizacionService puntosFidelizacionService) {
        this.puntosFidelizacionService = puntosFidelizacionService;
    }

    // Obtener todos los puntos de fidelización
    @GetMapping("/todos")
    public ResponseEntity<List<PuntosFidelizacionDTO>> obtenerTodosPuntos() {
        List<PuntosFidelizacionDTO> puntos = puntosFidelizacionService.obtenerTodosPuntosFidelizacion();
        return ResponseEntity.ok(puntos);
    }

    // Obtener un punto de fidelización por ID
    @GetMapping("/{id}")
    public ResponseEntity<PuntosFidelizacionDTO> obtenerPuntoPorId(@PathVariable("id") Long id) {
        PuntosFidelizacionDTO punto = puntosFidelizacionService.obtenerPuntoFidelizacionPorId(id);
        return punto != null ? ResponseEntity.ok(punto) : ResponseEntity.notFound().build();
    }

    // Obtener puntos de fidelización por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PuntosFidelizacionDTO>> obtenerPuntosPorUsuario(@PathVariable("usuarioId") Integer usuarioId) {
        List<PuntosFidelizacionDTO> puntos = puntosFidelizacionService.obtenerPuntosPorUsuario(usuarioId);
        return ResponseEntity.ok(puntos);
    }

    // Crear un nuevo punto de fidelización
    @PostMapping("/nuevo")
    public ResponseEntity<PuntosFidelizacionDTO> crearPuntoFidelizacion(@RequestBody PuntosFidelizacionDTO puntosFidelizacionDTO) {
        PuntosFidelizacionDTO creado = puntosFidelizacionService.crearPuntoFidelizacion(puntosFidelizacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar un punto de fidelización
    @PutMapping("/{id}")
    public ResponseEntity<PuntosFidelizacionDTO> actualizarPuntoFidelizacion(@PathVariable("id") Long id, @RequestBody PuntosFidelizacionDTO puntosFidelizacionDTO) {
        PuntosFidelizacionDTO actualizado = puntosFidelizacionService.actualizarPuntoFidelizacion(id, puntosFidelizacionDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un punto de fidelización
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuntoFidelizacion(@PathVariable("id") Long id) {
        puntosFidelizacionService.eliminarPuntoFidelizacion(id);
        return ResponseEntity.noContent().build();
    }
}