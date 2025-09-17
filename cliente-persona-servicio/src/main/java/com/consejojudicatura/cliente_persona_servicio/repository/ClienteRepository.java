package com.consejojudicatura.cliente_persona_servicio.repository;

import com.consejojudicatura.cliente_persona_servicio.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findByEstado(Boolean estado);
    Optional<Cliente> findByClienteIdAndEstado(UUID clienteId, Boolean estado);
}
