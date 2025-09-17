package com.consejojudicatura.cuenta_movimiento_servicio.services;

import com.consejojudicatura.cuenta_movimiento_servicio.entity.Cuenta;
import com.consejojudicatura.cuenta_movimiento_servicio.respository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> listaCuentas(){
        return cuentaRepository.findByEstado(true);
    }

    //Almacenar Cuenta
    public ResponseEntity<Object> guardarCuenta(Cuenta cuenta){
        Optional<Cuenta> existeCuenta = cuentaRepository.findByNumeroCuentaAndEstado(cuenta.getNumeroCuenta(), true);
        HashMap<String, Object> respuesta = new HashMap<>();

        if(existeCuenta.isPresent()){
            respuesta.put("mensaje", "La cuenta ya existe");
            return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
        }

        cuentaRepository.save(cuenta);
        respuesta.put("mensaje", "Registro almacenado");
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    //Actualizar cuenta
    public ResponseEntity<Object> actualizarCuenta(Cuenta cuenta){
        HashMap<String, Object> respuesta = new HashMap<>();

        if (cuenta.getCuentaId() == null) {
            respuesta.put("mensaje", "No se puede actualizar el registro");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        cuentaRepository.save(cuenta);
        respuesta.put("mensaje", "Registro actualizado");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //Eliminar cuenta
    public ResponseEntity<Object> eliminarCuenta(UUID id){
        HashMap<String, Object> respuesta = new HashMap<>();

        Optional<Cuenta> existeCuenta = cuentaRepository.findByCuentaIdAndEstado(id, true);
        if (existeCuenta.isPresent()) {
            existeCuenta.get().setEstado(false);
            cuentaRepository.save(existeCuenta.get());
            respuesta.put("mensaje", "Registro eliminado");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }

        respuesta.put("mensaje", "No se puede eliminar el registro");
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);

    }
}
