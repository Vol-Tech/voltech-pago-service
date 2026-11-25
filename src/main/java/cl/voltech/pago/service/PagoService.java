package cl.voltech.pago.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.voltech.pago.dto.BoletaResponse;
import cl.voltech.pago.dto.PagarRequest;
import cl.voltech.pago.dto.PagoResponse;
import cl.voltech.pago.exception.BoletaNoDisponibleException;
import cl.voltech.pago.exception.ResourceNotFoundException;
import cl.voltech.pago.mapper.PagoMapper;
import cl.voltech.pago.model.Pago;
import cl.voltech.pago.repository.PagoRepo;

@Service
public class PagoService {
  private static final int LIMITE_MONTO = 1000000;

  private final PagoRepo pagoRepo;

  public PagoService(PagoRepo pagoRepo) {
    this.pagoRepo = pagoRepo;
  }

  public PagoResponse pagar(PagarRequest request) {
    String estado = "APROBADO";
    String motivo = null;

    if (request.monto() > LIMITE_MONTO) {
      estado = "RECHAZADO";
      motivo = "Fondos insuficientes: el monto supera el límite de la tarjeta";
    }

    Pago pago = pagoRepo.save(PagoMapper.toModel(request, estado, motivo));

    return PagoMapper.toResponse(pago);
  }

  public PagoResponse obtenerPago(int id) {
    return PagoMapper.toResponse(buscarPago(id));
  }

  public List<PagoResponse> obtenerPagosPedido(int pedidoId) {
    List<Pago> pagos = pagoRepo.findByPedidoIdOrderByFechaCreacionDesc(pedidoId);
    List<PagoResponse> respuestas = new ArrayList<>();

    for (Pago pago : pagos) {
      respuestas.add(PagoMapper.toResponse(pago));
    }

    return respuestas;
  }

  public BoletaResponse obtenerBoleta(int id) {
    Pago pago = buscarPago(id);

    if (!"APROBADO".equals(pago.getEstado())) {
      throw new BoletaNoDisponibleException("No hay boleta: el pago " + id + " fue rechazado");
    }

    return PagoMapper.toBoleta(pago);
  }

  private Pago buscarPago(int id) {
    return pagoRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id " + id));
  }
}