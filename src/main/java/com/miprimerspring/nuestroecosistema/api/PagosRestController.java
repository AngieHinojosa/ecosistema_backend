package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.PagoDTO;
import com.miprimerspring.nuestroecosistema.service.PagosService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con los pagos de pedidos")
public class PagosRestController {

    private final PagosService pagosService;

    @Autowired
    public PagosRestController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @Operation(summary = "Crear un nuevo pago", tags = {"Pagos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de pago inválidos")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<PagoDTO> crearPago(@Valid @RequestBody PagoDTO pagoDTO) {
        PagoDTO createdPago = pagosService.crearPago(pagoDTO);
        return ResponseEntity.ok(createdPago);
    }

    @Operation(summary = "Obtener pago por ID", tags = {"Pagos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPagoPorId(@PathVariable Long id) {
        PagoDTO pagoDTO = pagosService.obtenerPagoPorId(id);
        return ResponseEntity.ok(pagoDTO);
    }

    @Operation(summary = "Obtener pagos por ID de pedido", tags = {"Pagos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagos encontrados por pedido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron pagos para este pedido")
    })
    @GetMapping("/porPedido/{pedidoId}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorPedidoId(@PathVariable Integer pedidoId) {
        List<PagoDTO> pagos = pagosService.obtenerPagosPorPedidoId(pedidoId);
        return ResponseEntity.ok(pagos);
    }

    @Operation(summary = "Obtener pagos por ID de cuenta", tags = {"Pagos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagos encontrados por cuenta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron pagos para esta cuenta")
    })
    @GetMapping("/porCuenta/{cuentaId}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorCuentaId(@PathVariable Integer cuentaId) {
        List<PagoDTO> pagos = pagosService.obtenerPagosPorCuentaId(cuentaId);
        return ResponseEntity.ok(pagos);
    }

    @Operation(summary = "Obtener pagos por método", tags = {"Pagos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagos encontrados por método", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron pagos para este método")
    })
    @GetMapping("/porMetodo/{metodo}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorMetodo(@PathVariable String metodo) {
        List<PagoDTO> pagos = pagosService.obtenerPagosPorMetodo(metodo);
        return ResponseEntity.ok(pagos);
    }

    @Operation(summary = "Obtener todos los pagos", tags = {"Pagos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los pagos obtenidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/todos")
    public ResponseEntity<List<PagoDTO>> obtenerTodosPagos() {
        List<PagoDTO> pagos = pagosService.obtenerTodosPagos();
        return ResponseEntity.ok(pagos);
    }

    @Operation(summary = "Actualizar un pago", tags = {"Pagos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizarPago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        PagoDTO updatedPago = pagosService.actualizarPago(id, pagoDTO);
        return ResponseEntity.ok(updatedPago);
    }

    @Operation(summary = "Eliminar un pago", tags = {"Pagos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pago eliminado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagosService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
