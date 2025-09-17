package com.consejojudicatura.cliente_persona_servicio.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ClienteDTO {
    private UUID clienteId;        // ID del cliente
    private String nombre;  // Nombre de la persona (cliente)

}
