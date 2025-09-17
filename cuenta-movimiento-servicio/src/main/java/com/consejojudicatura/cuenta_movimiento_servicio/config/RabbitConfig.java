package com.consejojudicatura.cuenta_movimiento_servicio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {

    public static final String CLIENTE_QUEUE = "cliente-queue";

    @Bean
    public Queue clienteQueue() {
        return new Queue(CLIENTE_QUEUE, true);
    }
}
