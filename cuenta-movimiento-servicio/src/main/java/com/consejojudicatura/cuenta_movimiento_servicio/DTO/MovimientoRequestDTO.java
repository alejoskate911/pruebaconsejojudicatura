package com.consejojudicatura.cuenta_movimiento_servicio.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoRequestDTO {
    private UUID cuentaId;
    private Float valor;
    private String tipoMovimiento;
}
