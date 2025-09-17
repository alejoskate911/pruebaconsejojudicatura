package com.consejojudicatura.cliente_persona_servicio.repository;

import com.consejojudicatura.cliente_persona_servicio.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, UUID> {
    Optional<Persona> findByIdentificacion(String identificacion);
}
