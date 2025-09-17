package com.consejojudicatura.cliente_persona_servicio.services;

import com.consejojudicatura.cliente_persona_servicio.DTO.ClienteDTO;
import com.consejojudicatura.cliente_persona_servicio.config.RabbitConfig;
import com.consejojudicatura.cliente_persona_servicio.entity.Cliente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientePublisher {
    private final RabbitTemplate rabbitTemplate;
    ClienteDTO dto = new ClienteDTO();

    public ClientePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarCliente(Cliente cliente) {
        rabbitTemplate.convertAndSend(RabbitConfig.CLIENTE_QUEUE, cliente);
        ClienteDTO dto = new ClienteDTO();
        dto.setClienteId(cliente.getClienteId());
        dto.setNombre(cliente.getPersona().getNombre());
        rabbitTemplate.convertAndSend(RabbitConfig.CLIENTE_QUEUE, dto);
        System.out.println("📤 Cliente enviado a RabbitMQ: " + dto.getNombre());
    }
}
