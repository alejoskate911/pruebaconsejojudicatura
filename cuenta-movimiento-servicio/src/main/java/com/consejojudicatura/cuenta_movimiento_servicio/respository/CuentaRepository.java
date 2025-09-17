package com.consejojudicatura.cuenta_movimiento_servicio.respository;

import com.consejojudicatura.cuenta_movimiento_servicio.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {
    List<Cuenta> findByEstado(Boolean estado);
    Optional<Cuenta> findByNumeroCuentaAndEstado(String numero_cuenta, Boolean estado);

    Optional<Cuenta> findByCuentaIdAndEstado(UUID cuentaId, Boolean estado);
}
