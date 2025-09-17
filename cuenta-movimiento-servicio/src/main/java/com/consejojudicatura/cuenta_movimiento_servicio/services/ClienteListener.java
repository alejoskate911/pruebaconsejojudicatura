package com.consejojudicatura.cuenta_movimiento_servicio.services;

import com.consejojudicatura.cuenta_movimiento_servicio.config.RabbitConfig;
import com.consejojudicatura.cuenta_movimiento_servicio.DTO.ClienteDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteListener {
    @RabbitListener(queues = RabbitConfig.CLIENTE_QUEUE)
    public void recibirCliente(ClienteDTO cliente) {
        System.out.println("📥 Cliente recibido en cuentas-movimientos: " + cliente.getNombre());
        // Aquí luego podrías crear automáticamente una cuenta o registrar en la BD
    }
}
