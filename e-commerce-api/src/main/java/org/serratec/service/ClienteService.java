package org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.domain.Cliente;
import org.serratec.dto.ClienteDTO2;
import org.serratec.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<ClienteDTO2> buscaTodos() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO2> clientesDTO = new ArrayList<>();
		 
		for (Cliente cliente : clientes) {
			clientesDTO.add(new ClienteDTO2(cliente));
		}

		 return clientesDTO;
	}

    public Optional<Cliente> findById(Long idCliente) {
        return clienteRepository.findById(idCliente);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }

}
