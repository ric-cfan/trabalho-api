package org.serratec.repository;

import java.util.Optional;

import org.serratec.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{
	Optional<Cliente> findById(Long id);
}
