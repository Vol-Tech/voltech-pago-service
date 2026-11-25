package cl.voltech.pago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.voltech.pago.model.Pago;

@Repository
public interface PagoRepo extends JpaRepository<Pago, Integer> {
  List<Pago> findByPedidoIdOrderByFechaCreacionDesc(int pedidoId);
}