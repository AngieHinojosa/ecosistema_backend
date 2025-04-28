package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.service.UsuarioDescuentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-descuentos")
@Tag(name = "UsuariosDescuentos", description = "Operaciones relacionadas con los descuentos asignados a los usuarios.")
public class UsuarioDescuentoRestController {

    @Autowired
    private UsuarioDescuentoService usuarioDescuentoService;

    @Operation(summary = "Obtener todos los descuentos de los usuarios", description = "Este endpoint permite obtener todos los descuentos asociados a los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Descuentos obtenidos exitosamente")
    })
    @GetMapping
    @Tag(name = "UsuariosDescuentos")
    public ResponseEntity<List<UsuarioDescuentoDTO>> obtenerUsuarioDescuentos() {
        List<UsuarioDescuentoDTO> descuentos = usuarioDescuentoService.obtenerUsuarioDescuentos();
        return ResponseEntity.ok(descuentos);
    }

    @Operation(summary = "Obtener descuento de un usuario por ID", description = "Este endpoint permite obtener un descuento específico de un usuario mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Descuento encontrado"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado")
    })
    @GetMapping("/{id}")
    @Tag(name = "UsuariosDescuentos")
    public ResponseEntity<UsuarioDescuentoDTO> obtenerUsuarioDescuento(@PathVariable Long id) {
        UsuarioDescuentoDTO descuento = usuarioDescuentoService.obtenerUsuarioDescuentoPorId(id);
        return descuento != null ? ResponseEntity.ok(descuento) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un descuento para un usuario", description = "Este endpoint permite crear un nuevo descuento para un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Descuento creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el descuento")
    })
    @PostMapping
    @Tag(name = "UsuariosDescuentos")
    public ResponseEntity<UsuarioDescuentoDTO> crearUsuarioDescuento(@RequestBody UsuarioDescuentoDTO usuarioDescuentoDTO) {
        UsuarioDescuentoDTO descuentoCreado = usuarioDescuentoService.crearUsuarioDescuento(usuarioDescuentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoCreado);
    }

    @Operation(summary = "Eliminar descuento de un usuario", description = "Este endpoint permite eliminar un descuento de un usuario mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Descuento eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Descuento no encontrado")
    })
    @DeleteMapping("/{id}")
    @Tag(name = "UsuariosDescuentos")
    public ResponseEntity<Void> eliminarUsuarioDescuento(@PathVariable Long id) {
        usuarioDescuentoService.eliminarUsuarioDescuento(id);
        return ResponseEntity.noContent().build();
    }
}
