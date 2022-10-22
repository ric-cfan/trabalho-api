package org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.domain.Cliente;
import org.serratec.domain.Endereco;
import org.serratec.dto.ClienteDTO1;
import org.serratec.dto.ClienteDTO2;
import org.serratec.dto.EnderecoViaCepDTO;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
			clientesDTO.add(new ClienteDTO2(cliente));
		}

		 return clientesDTO;
	}

	public Optional<ClienteDTO2> findById(Long idCliente) {
        Optional<ClienteDTO2> cliente = Optional.ofNullable(new ClienteDTO2(clienteRepository.findById(idCliente).get()));
        return cliente;
    }

    public ClienteDTO2 cadastrar(ClienteDTO1 cliente) {
    	Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cliente.getCep()));
		if (endereco.isPresent()) {
			
			EnderecoViaCepDTO enderecoBanco = new EnderecoViaCepDTO(endereco.get());
			Endereco enderecoCadastro = new Endereco(enderecoBanco,cliente);
			Cliente clienteBanco = new Cliente(cliente, enderecoCadastro);
			clienteRepository.save(clienteBanco);
			ClienteDTO2 clienteDTO2 = new ClienteDTO2(clienteBanco);
			return clienteDTO2;
		} else {

			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://viacep.com.br/ws/" + cliente.getCep() + "/json";

			Optional<EnderecoViaCepDTO> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uri, EnderecoViaCepDTO.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				EnderecoViaCepDTO enderecoBanco = new EnderecoViaCepDTO();
				enderecoBanco.setCep(enderecoViaCep.get().getCep());
				enderecoBanco.setBairro(enderecoViaCep.get().getBairro());
				enderecoBanco.setComplemento(enderecoViaCep.get().getComplemento());
				enderecoBanco.setLocalidade(enderecoViaCep.get().getLocalidade());
				enderecoBanco.setLogradouro(enderecoViaCep.get().getLogradouro());
				enderecoBanco.setUf(enderecoViaCep.get().getUf());
				
				Endereco enderecoCadastro = new Endereco(enderecoBanco,cliente);
				Cliente clienteBanco = new Cliente(cliente, enderecoCadastro);
				clienteRepository.save(clienteBanco);
				ClienteDTO2 clienteDTO2 = new ClienteDTO2(clienteBanco);
				return clienteDTO2;
				
			} else {
				return null;
			}
			
		}
	
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente) ;
    }
    
    public void deleteById(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }


}
