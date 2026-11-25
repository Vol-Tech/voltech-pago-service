package cl.voltech.pago.dto;

import java.time.LocalDateTime;

public record PagoResponse(
    int id,
    int pedidoId,
    int monto,
    String metodo,
    String estado,
    String motivo,
    String numeroBoleta,
    LocalDateTime fechaCreacion) {
}