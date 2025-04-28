package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.PedidoDetalleDTO;
import com.miprimerspring.nuestroecosistema.service.PedidoDetalleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-detalle")
@Tag(name = "PedidoDetalle", description = "Operaciones relacionadas con los detalles de los pedidos")
public class PedidoDetalleRestController {

    private final PedidoDetalleService pedidoDetalleService;

    @Autowired
    public PedidoDetalleRestController(PedidoDetalleService pedidoDetalleService) {
        this.pedidoDetalleService = pedidoDetalleService;
    }

    @Operation(summary = "Crear un nuevo detalle de pedido", tags = {"PedidoDetalle"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle de pedido creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDetalleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos del detalle de pedido inválidos")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<PedidoDetalleDTO> crearPedidoDetalle(@RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        PedidoDetalleDTO createdPedidoDetalle = pedidoDetalleService.crearPedidoDetalle(pedidoDetalleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPedidoDetalle);
    }

    @Operation(summary = "Obtener detalle de pedido por ID", tags = {"PedidoDetalle"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de pedido encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDetalleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> obtenerPedidoDetallePorId(@PathVariable Long id) {
        PedidoDetalleDTO pedidoDetalleDTO = pedidoDetalleService.obtenerPedidoDetallePorId(id);
        if (pedidoDetalleDTO != null) {
            return ResponseEntity.ok(pedidoDetalleDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtener detalles de pedido por ID de pedido", tags = {"PedidoDetalle"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles de pedido encontrados por ID de pedido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron detalles para este pedido")
    })
    @GetMapping("/porPedido/{pedidoId}")
    public ResponseEntity<List<PedidoDetalleDTO>> obtenerDetallesPorPedido(@PathVariable Integer pedidoId) {
        List<PedidoDetalleDTO> detalles = pedidoDetalleService.obtenerDetallesPorPedido(pedidoId);
        return ResponseEntity.ok(detalles);
    }

    @Operation(summary = "Obtener detalles de pedido por ID de producto", tags = {"PedidoDetalle"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles de pedido encontrados por ID de producto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron detalles para este producto")
    })
    @GetMapping("/porProducto/{productoId}")
    public ResponseEntity<List<PedidoDetalleDTO>> obtenerDetallesPorProducto(@PathVariable Integer productoId) {
        List<PedidoDetalleDTO> detalles = pedidoDetalleService.obtenerDetallesPorProducto(productoId);
        return ResponseEntity.ok(detalles);
    }

    @Operation(summary = "Obtener todos los detalles de pedido", tags = {"PedidoDetalle"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los detalles de pedido obtenidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/todos")
    public ResponseEntity<List<PedidoDetalleDTO>> obtenerTodosDetalles() {
        List<PedidoDetalleDTO> detalles = pedidoDetalleService.obtenerTodosDetalles();
        return ResponseEntity.ok(detalles);
    }

    @Operation(summary = "Actualizar un detalle de pedido", tags = {"PedidoDetalle"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de pedido actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDetalleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PedidoDetalleDTO> actualizarPedidoDetalle(@PathVariable Long id, @RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        PedidoDetalleDTO updatedPedidoDetalle = pedidoDetalleService.actualizarPedidoDetalle(id, pedidoDetalleDTO);
        if (updatedPedidoDetalle != null) {
            return ResponseEntity.ok(updatedPedidoDetalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un detalle de pedido", tags = {"PedidoDetalle"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Detalle de pedido eliminado"),
            @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedidoDetalle(@PathVariable Long id) {
        pedidoDetalleService.eliminarPedidoDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
