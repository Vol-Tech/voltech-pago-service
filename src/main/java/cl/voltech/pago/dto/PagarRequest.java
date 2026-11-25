package cl.voltech.pago.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record PagarRequest(
    @Positive(message = "El pedido es obligatorio")
    int pedidoId,

    @Positive(message = "El monto debe ser mayor a 0")
    int monto,

    @NotBlank(message = "El método de pago es obligatorio")
    @Pattern(regexp = "DEBITO|CREDITO|TRANSFERENCIA", message = "El método debe ser DEBITO, CREDITO o TRANSFERENCIA")
    String metodo
) {
}