package com.consejojudicatura.cuenta_movimiento_servicio.respository;

import com.consejojudicatura.cuenta_movimiento_servicio.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, UUID> {
    List<Movimientos> findAll();
}
