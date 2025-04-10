package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.DTO.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;
import com.miprimerspring.nuestroecosistema.service.DescuentoAplicadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/descuento-aplicado")
@Validated
public class DescuentoAplicadoRestController {

    //Crear descuento, obtener descuentos, aplicar descuento.
    @Autowired
    private DescuentoAplicadoService descuentoAplicadoService;

    // Obtener todos los descuentos aplicados
    @GetMapping("/lista")
    public ResponseEntity<List<DescuentoAplicadoDTO>> listar() {
        List<DescuentoAplicadoDTO> descuentos = descuentoAplicadoService.obtenerTodosLosDescuentosAplicados();
        return ResponseEntity.ok(descuentos);
    }

    // Obtener un descuento aplicado por su ID
    @GetMapping("/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> obtener(@PathVariable Long id) {
        DescuentoAplicadoDTO descuento = descuentoAplicadoService.obtenerDescuentoAplicadoPorId(id);
        if (descuento != null) {
            return ResponseEntity.ok(descuento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Crear un nuevo descuento aplicado
    @PostMapping("/nuevo")
    public ResponseEntity<DescuentoAplicadoDTO> crear(@Valid @RequestBody DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicadoDTO descuentoCreado = descuentoAplicadoService.crearDescuentoAplicado(descuentoAplicadoDTO);
        return new ResponseEntity<>(descuentoCreado, HttpStatus.CREATED);
    }

    // Actualizar un descuento aplicado existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicadoDTO descuentoActualizado = descuentoAplicadoService.actualizarDescuentoAplicado(id, descuentoAplicadoDTO);
        if (descuentoActualizado != null) {
            return ResponseEntity.ok(descuentoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Eliminar un descuento aplicado por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        descuentoAplicadoService.eliminarDescuentoAplicado(id);
        return ResponseEntity.noContent().build();
    }
}

