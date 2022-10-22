package org.serratec.repository;

import java.util.Optional;

import org.serratec.domain.Cliente;
import org.serratec.domain.Endereco;
import org.serratec.dto.ClienteDTO1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{
	Optional<Cliente> findById(Long id);
	
//	Cliente cadastro(ClienteDTO1 clienteDTO,Endereco enderecoCadastro2);

//	Cliente save(ClienteDTO1 cliente);

	
	
	
}
