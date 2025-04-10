package com.miprimerspring.nuestroecosistema.api;

import com.miprimerspring.nuestroecosistema.DTO.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.service.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaBancariaRestController {

    //Crear cuenta bancaria, obtener cuenta bancaria, actualizar cuenta bancaria.
    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @GetMapping("/lista")
    public ResponseEntity<List<CuentaBancariaDTO>> listarCuentas() {
        List<CuentaBancariaDTO> cuentasDTO = cuentaBancariaService.obtenerTodasLasCuentaBancarias();
        return ResponseEntity.ok(cuentasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaBancariaDTO> obtenerCuenta(@PathVariable Long id) {
        CuentaBancariaDTO cuentaDTO = cuentaBancariaService.obtenerCuentaBancariaPorId(id);
        if (cuentaDTO != null) {
            return ResponseEntity.ok(cuentaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/nueva")
    public ResponseEntity<CuentaBancaria> crearCuenta(@RequestBody CuentaBancaria cuenta) {
        CuentaBancaria nuevaCuenta = cuentaBancariaService.crearCuentaBancaria(cuenta);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CuentaBancaria> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaBancaria cuenta) {
        CuentaBancaria cuentaActualizada = cuentaBancariaService.actualizarCuentaBancaria(id, cuenta);
        return ResponseEntity.ok(cuentaActualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaBancariaService.eliminarCuentaBancaria(id);
        return ResponseEntity.noContent().build();
    }
}
