package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.MensajeDTO;
import com.miprimerspring.nuestroecosistema.service.MensajeService;
import com.miprimerspring.nuestroecosistema.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensajes")
public class MensajeRestController {

    private final MensajeService mensajeService;

    @Autowired
    public MensajeRestController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<MensajeDTO> crearMensaje(@RequestBody MensajeDTO mensajeDTO) {
        MensajeDTO createdMensaje = mensajeService.crearMensaje(mensajeDTO);
        return new ResponseEntity<>(createdMensaje, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO> obtenerMensaje(@PathVariable Long id) {
        MensajeDTO mensajeDTO = mensajeService.obtenerMensajePorId(id);
        return new ResponseEntity<>(mensajeDTO, HttpStatus.OK);
    }

    @GetMapping("/emisor/{emisorId}")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajesPorEmisorId(@PathVariable Integer emisorId) {
        List<MensajeDTO> mensajes = mensajeService.obtenerMensajesPorEmisorId(emisorId);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @GetMapping("/receptor/{receptorId}")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajesPorReceptorId(@PathVariable Integer receptorId) {
        List<MensajeDTO> mensajes = mensajeService.obtenerMensajesPorReceptorId(receptorId);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @GetMapping("/leido/{mensajeLeido}")
    public ResponseEntity<List<MensajeDTO>> obtenerMensajesPorLeido(@PathVariable Boolean mensajeLeido) {
        List<MensajeDTO> mensajes = mensajeService.obtenerMensajesPorLeido(mensajeLeido);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MensajeDTO>> obtenerTodosMensajes() {
        List<MensajeDTO> mensajes = mensajeService.obtenerTodosMensajes();
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO> actualizarMensaje(@PathVariable Long id, @RequestBody MensajeDTO mensajeDTO) {
        MensajeDTO updatedMensaje = mensajeService.actualizarMensaje(id, mensajeDTO);
        return new ResponseEntity<>(updatedMensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}