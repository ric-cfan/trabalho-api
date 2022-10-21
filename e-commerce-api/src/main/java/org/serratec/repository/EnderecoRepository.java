package org.serratec.repository;

import org.serratec.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository <Endereco, Long>{
	Endereco getById(Long Id);
}