package com.consejojudicatura.cuenta_movimiento_servicio.entity;

import com.consejojudicatura.cuenta_movimiento_servicio.services.ClienteListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cuentaId;
    private String numeroCuenta;
    private String tipoCuenta;
    private Float saldoInicial;
    private Boolean estado;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimientos> movimientos;


}
