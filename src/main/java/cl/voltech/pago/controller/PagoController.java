package cl.voltech.pago.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.voltech.pago.dto.BoletaResponse;
import cl.voltech.pago.dto.PagarRequest;
import cl.voltech.pago.dto.PagoResponse;
import cl.voltech.pago.service.PagoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pagos")
@CrossOrigin(origins = "http://localhost:5173")
public class PagoController {

  private final PagoService pagoService;

  public PagoController(PagoService pagoService) {
    this.pagoService = pagoService;
  }

  @PostMapping
  public ResponseEntity<PagoResponse> pagar(@Valid @RequestBody PagarRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.pagar(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PagoResponse> obtenerPago(@PathVariable("id") int id) {
    return ResponseEntity.ok(pagoService.obtenerPago(id));
  }

  @GetMapping("/{id}/boleta")
  public ResponseEntity<BoletaResponse> obtenerBoleta(@PathVariable("id") int id) {
    return ResponseEntity.ok(pagoService.obtenerBoleta(id));
  }

  @GetMapping("/pedido/{pedidoId}")
  public ResponseEntity<List<PagoResponse>> obtenerPagosPedido(@PathVariable("pedidoId") int pedidoId) {
    return ResponseEntity.ok(pagoService.obtenerPagosPedido(pedidoId));
  }
}