package cl.voltech.pago.mapper;

import cl.voltech.pago.dto.BoletaResponse;
import cl.voltech.pago.dto.PagarRequest;
import cl.voltech.pago.dto.PagoResponse;
import cl.voltech.pago.model.Pago;

public interface PagoMapper {

  public static Pago toModel(PagarRequest request, String estado, String motivo) {
    return new Pago(0, request.pedidoId(), request.monto(), request.metodo(), estado, motivo, null);
  }

  public static String numeroBoleta(Pago pago) {
    if (!"APROBADO".equals(pago.getEstado())) {
      return null;
    }
    return String.format("B-%06d", pago.getId());
  }

  public static PagoResponse toResponse(Pago pago) {
    return new PagoResponse(
        pago.getId(),
        pago.getPedidoId(),
        pago.getMonto(),
        pago.getMetodo(),
        pago.getEstado(),
        pago.getMotivo(),
        numeroBoleta(pago),
        pago.getFechaCreacion());
  }

  public static BoletaResponse toBoleta(Pago pago) {
    int neto = (int) Math.round(pago.getMonto() / 1.19);
    int iva = pago.getMonto() - neto;

    return new BoletaResponse(
        numeroBoleta(pago),
        pago.getFechaCreacion(),
        pago.getPedidoId(),
        pago.getMetodo(),
        neto,
        iva,
        pago.getMonto());
  }
}