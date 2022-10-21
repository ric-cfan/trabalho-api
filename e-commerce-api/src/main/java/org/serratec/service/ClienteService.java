package org.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.domain.Cliente;
import org.serratec.domain.Endereco;
import org.serratec.dto.ClienteDTO2;
import org.serratec.dto.EnderecoDTO;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<ClienteDTO2> buscaTodos() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO2> clientesDTO = new ArrayList<>();
		 
		for (Cliente cliente : clientes) {
			Endereco endereco = enderecoRepository.getById(cliente.getEndereco().getId());
			EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
			clientesDTO.add(new ClienteDTO2(cliente, enderecoDTO));
		}

		 return clientesDTO;
	}

}
