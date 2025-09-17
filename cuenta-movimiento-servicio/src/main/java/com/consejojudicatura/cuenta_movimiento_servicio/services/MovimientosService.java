package com.consejojudicatura.cuenta_movimiento_servicio.services;

import com.consejojudicatura.cuenta_movimiento_servicio.DTO.MovimientoRequestDTO;
import com.consejojudicatura.cuenta_movimiento_servicio.entity.Cuenta;
import com.consejojudicatura.cuenta_movimiento_servicio.entity.Movimientos;
import com.consejojudicatura.cuenta_movimiento_servicio.respository.CuentaRepository;
import com.consejojudicatura.cuenta_movimiento_servicio.respository.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientosService {

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Movimientos> listaMovimientos(){
        return movimientosRepository.findAll();
    }

    public ResponseEntity<Object> guardarMovimiento(MovimientoRequestDTO movimiento){
        HashMap<String, Object> respuesta = new HashMap<>();

        // Buscar cuenta
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        float nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor(); // si es negativo resta, si positivo suma

        if (nuevoSaldo < 0) {
            respuesta.put("mensaje", "Saldo no disponible");
            return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
        }

        // Actualizar saldo de la cuenta
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        // Guardar movimiento
        Movimientos movimientos = Movimientos.builder()
                .cuenta(cuenta)
                .valor(movimiento.getValor())
                .tipoMovimiento(movimiento.getValor() < 0 ? "Retiro de "+movimiento.getValor()*-1 : "Deposito de "+movimiento.getValor())
                .fecha(new Date())
                .saldo(nuevoSaldo)
                .build();

        movimientosRepository.save(movimientos);

        respuesta.put("mensaje", "Registro almacenado");
        respuesta.put("movimiento", movimiento);

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    //Actualizar movimiento
    public ResponseEntity<Object> actualizarMovimiento(Movimientos movimiento){
        HashMap<String, Object> respuesta = new HashMap<>();

        if (movimiento.getMovimientosId() == null) {
            respuesta.put("mensaje", "No se puede actualizar el registro");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        movimientosRepository.save(movimiento);
        respuesta.put("mensaje", "Registro actualizado");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
