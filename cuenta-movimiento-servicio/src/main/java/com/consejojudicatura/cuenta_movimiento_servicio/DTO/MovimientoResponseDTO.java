package com.consejojudicatura.cuenta_movimiento_servicio.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoResponseDTO {
    private UUID movimientosId;
    private UUID cuentaId;
    private Float valor;
    private Float saldo; // saldo resultante después del movimiento
    private String tipoMovimiento;
    private Date fecha;
}
