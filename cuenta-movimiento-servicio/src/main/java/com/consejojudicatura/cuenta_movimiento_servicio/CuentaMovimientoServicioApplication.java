package com.consejojudicatura.cuenta_movimiento_servicio;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CuentaMovimientoServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentaMovimientoServicioApplication.class, args);
	}

}
