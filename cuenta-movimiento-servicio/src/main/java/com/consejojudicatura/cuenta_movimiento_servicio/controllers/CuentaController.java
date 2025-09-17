package com.consejojudicatura.cuenta_movimiento_servicio.controllers;

import com.consejojudicatura.cuenta_movimiento_servicio.entity.Cuenta;
import com.consejojudicatura.cuenta_movimiento_servicio.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> listaCuentas(){
        return cuentaService.listaCuentas();
    }

    @PostMapping
    public ResponseEntity<Object> guardarCuenta(@RequestBody  Cuenta cuenta){
        return cuentaService.guardarCuenta(cuenta);
    }
}
