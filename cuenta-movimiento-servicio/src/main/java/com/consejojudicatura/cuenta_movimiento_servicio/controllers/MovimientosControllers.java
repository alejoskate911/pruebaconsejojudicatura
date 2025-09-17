package com.consejojudicatura.cuenta_movimiento_servicio.controllers;

import com.consejojudicatura.cuenta_movimiento_servicio.DTO.MovimientoRequestDTO;
import com.consejojudicatura.cuenta_movimiento_servicio.entity.Movimientos;
import com.consejojudicatura.cuenta_movimiento_servicio.services.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimiento")
public class MovimientosControllers {

    @Autowired
    private MovimientosService movimientosService;

    @GetMapping
    public List<Movimientos> listaMovimientos(){
        return movimientosService.listaMovimientos();
    }

    @PostMapping
    public ResponseEntity<Object> guardarMovimiento(@RequestBody MovimientoRequestDTO movimiento){
        return movimientosService.guardarMovimiento(movimiento);

    }
}
