package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.dto.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.service.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaBancariaRestController {

    private final CuentaBancariaService cuentaBancariaService;

    @Autowired
    public CuentaBancariaRestController(CuentaBancariaService cuentaBancariaService) {
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaBancariaDTO> obtenerCuentaBancaria(@PathVariable Long id) {
        CuentaBancariaDTO cuentaBancariaDTO = cuentaBancariaService.obtenerCuentaBancariaPorId(id);
        return new ResponseEntity<>(cuentaBancariaDTO, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CuentaBancariaDTO>> obtenerCuentasPorUsuario(@PathVariable Integer usuarioId) {
        List<CuentaBancariaDTO> cuentas = cuentaBancariaService.obtenerCuentasPorUsuario(Long.valueOf(usuarioId));
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CuentaBancariaDTO>> obtenerTodasCuentas() {
        List<CuentaBancariaDTO> cuentas = cuentaBancariaService.obtenerTodasCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaBancariaDTO> actualizarCuentaBancaria(@PathVariable Long id, @RequestBody CuentaBancariaDTO cuentaBancariaDTO) {
        CuentaBancariaDTO updatedCuentaBancaria = cuentaBancariaService.actualizarCuentaBancaria(id, cuentaBancariaDTO);
        return new ResponseEntity<>(updatedCuentaBancaria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaBancaria(@PathVariable Long id) {
        cuentaBancariaService.eliminarCuentaBancaria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}