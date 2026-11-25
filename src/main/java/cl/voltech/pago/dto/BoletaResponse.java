package cl.voltech.pago.dto;

import java.time.LocalDateTime;

public record BoletaResponse(
    String numeroBoleta,
    LocalDateTime fecha,
    int pedidoId,
    String metodo,
    int neto,
    int iva,
    int total) {
}