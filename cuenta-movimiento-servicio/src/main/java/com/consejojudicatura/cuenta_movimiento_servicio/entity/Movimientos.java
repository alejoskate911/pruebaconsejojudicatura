package com.consejojudicatura.cuenta_movimiento_servicio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID movimientosId;

    private Date fecha;
    private String tipoMovimiento;
    private Float valor;
    private Float saldo;

    @ManyToOne
    @JoinColumn(name = "cuentaId")
    private Cuenta cuenta;
}
