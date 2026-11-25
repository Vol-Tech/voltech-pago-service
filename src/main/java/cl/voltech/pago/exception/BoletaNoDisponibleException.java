package cl.voltech.pago.exception;

public class BoletaNoDisponibleException extends RuntimeException {
  public BoletaNoDisponibleException(String mensaje) {
    super(mensaje);
  }
}