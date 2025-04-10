package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.DTO.MensajeDTO;
import com.miprimerspring.nuestroecosistema.model.Mensaje;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.service.MensajeService;
import com.miprimerspring.nuestroecosistema.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mensaje")
public class MensajeRestController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private UsuarioService usuarioService; // Asegúrate de tener el servicio de Usuario

    // Obtener todos los mensajes, transformados a DTOs
    @GetMapping("/lista")
    public ResponseEntity<List<MensajeDTO>> listar() {
        // Obtener los mensajes como DTOs
        List<MensajeDTO> mensajeDTOs = mensajeService.obtenerTodosLosMensajes();
        return ResponseEntity.ok(mensajeDTOs);
    }

    // Obtener un mensaje por su ID, transformado a DTO
    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO> obtener(@PathVariable Long id) {
        // Obtener el mensaje como un DTO
        MensajeDTO mensajeDTO = mensajeService.obtenerMensajePorId(id);

        // Verificar si el mensajeDTO es null o no encontrado
        if (mensajeDTO != null) {
            return ResponseEntity.ok(mensajeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo mensaje, pasando un DTO y transformando la respuesta a DTO
    @PostMapping("/nuevo")
    public ResponseEntity<MensajeDTO> crear(@RequestBody MensajeDTO mensajeDTO) {
        // Convierte el DTO en la entidad Mensaje
        MensajeDTO mensajeGuardadoDTO = mensajeService.crearMensaje(mensajeDTO);  // Esto ahora retorna un DTO

        return new ResponseEntity<>(mensajeGuardadoDTO, HttpStatus.CREATED); // Devolver el DTO
    }

    // Eliminar un mensaje
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        return ResponseEntity.noContent().build();
    }
}