package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.VendedorDTO;
import com.miprimerspring.nuestroecosistema.model.Vendedor;
import com.miprimerspring.nuestroecosistema.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorRestController {

    private final VendedorService vendedorService;

    @Autowired
    public VendedorRestController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    // Crear un nuevo vendedor
    @PostMapping("/nuevo")
    public ResponseEntity<VendedorDTO> crearVendedor(@RequestBody VendedorDTO vendedorDTO) {
        VendedorDTO vendedor = vendedorService.crearVendedor(vendedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedor);
    }

    // Obtener vendedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> obtenerVendedorPorId(@PathVariable("id") Long id) {
        VendedorDTO vendedor = vendedorService.obtenerVendedorPorId(id);
        return vendedor != null ? ResponseEntity.ok(vendedor) : ResponseEntity.notFound().build();
    }

    // Obtener vendedor por ID de usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<VendedorDTO> obtenerVendedorPorUsuarioId(@PathVariable("usuarioId") Integer usuarioId) {
        VendedorDTO vendedor = vendedorService.obtenerVendedorPorUsuarioId(usuarioId);
        return vendedor != null ? ResponseEntity.ok(vendedor) : ResponseEntity.notFound().build();
    }

    // Obtener vendedores por nombre de tienda
    @GetMapping("/nombre-tienda/{nombreTienda}")
    public ResponseEntity<List<VendedorDTO>> obtenerVendedoresPorNombreTienda(@PathVariable("nombreTienda") String nombreTienda) {
        List<VendedorDTO> vendedores = vendedorService.obtenerVendedoresPorNombreTienda(nombreTienda);
        return ResponseEntity.ok(vendedores);
    }

    // Obtener vendedores por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<VendedorDTO>> obtenerVendedoresPorEstado(@PathVariable("estado") String estado) {
        List<VendedorDTO> vendedores = vendedorService.obtenerVendedoresPorEstado(estado);
        return ResponseEntity.ok(vendedores);
    }

    // Eliminar vendedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVendedor(@PathVariable("id") Long id) {
        vendedorService.eliminarVendedor(id);
        return ResponseEntity.noContent().build();
    }
}