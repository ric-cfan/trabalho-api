package org.serratec.repository;

import java.util.Optional;

import org.serratec.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	Optional<Pedido> findById(Long id);
}
