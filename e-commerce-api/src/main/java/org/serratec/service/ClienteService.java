package org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.domain.Cliente;
import org.serratec.domain.Endereco;
import org.serratec.dto.ClienteDTO1;
import org.serratec.dto.ClienteDTO2;
import org.serratec.dto.EnderecoViaCepDTO;
import org.serratec.exception.CpfException;
import org.serratec.exception.EmailException;
import org.serratec.exception.NotFoundErroException;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	public ClienteDTO2 findById(Long idCliente) {
		if (clienteRepository.findById(idCliente).isPresent()) {
			ClienteDTO2 cliente = new ClienteDTO2(clienteRepository.findById(idCliente).get());
			return cliente;
		}
		throw new NotFoundErroException("Cliente não encontrado!");
	}

	@Transactional
	public ClienteDTO2 cadastrar(ClienteDTO1 cliente) throws EmailException, CpfException {
		Cliente clienteValidaEmail = clienteRepository.findByEmail(cliente.getEmail());
		Cliente clienteValidaCpf = clienteRepository.findByCpf(cliente.getCpf());

		if (clienteValidaCpf != null) {
			throw new CpfException("Já existe um cliente com o cpf " + cliente.getCpf());
		}

		if (clienteValidaEmail != null) {
			throw new EmailException("Já existe um cliente com o e-mail " + cliente.getEmail());
		}

		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cliente.getCep()));
		if (endereco.isPresent()) {

			EnderecoViaCepDTO enderecoBanco = new EnderecoViaCepDTO(endereco.get());
			Endereco enderecoCadastro = new Endereco(enderecoBanco, cliente);
			Cliente clienteBanco = new Cliente(cliente, enderecoCadastro);
			clienteRepository.save(clienteBanco);
			ClienteDTO2 clienteDTO2 = new ClienteDTO2(clienteBanco);
			return clienteDTO2;
		} else {

			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://viacep.com.br/ws/" + cliente.getCep() + "/json";

			Optional<EnderecoViaCepDTO> enderecoViaCep = Optional
					.ofNullable(restTemplate.getForObject(uri, EnderecoViaCepDTO.class));
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

				Endereco enderecoCadastro = new Endereco(enderecoBanco, cliente);
				Cliente clienteBanco = new Cliente(cliente, enderecoCadastro);
				clienteRepository.save(clienteBanco);
				ClienteDTO2 clienteDTO2 = new ClienteDTO2(clienteBanco);
				return clienteDTO2;

			} else {
				return null;
			}

		}

	}

	@Transactional
	public ClienteDTO2 atualizar(ClienteDTO1 cliente, Long idCliente) {

		if (!clienteRepository.existsById(idCliente)) {
			throw new NotFoundErroException("Cliente não encontrado!");
		}

		cliente.setIdCliente(idCliente);
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cliente.getCep()));

		if (endereco.isPresent()) {

			EnderecoViaCepDTO enderecoBanco = new EnderecoViaCepDTO(endereco.get());
			Endereco enderecoCadastro = new Endereco(enderecoBanco, cliente);
			Cliente clienteBanco = new Cliente(cliente, enderecoCadastro);
			clienteRepository.save(clienteBanco);

			ClienteDTO2 clienteDTO2 = new ClienteDTO2(clienteRepository.findById(idCliente).get());
			return clienteDTO2;
		} else {

			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://viacep.com.br/ws/" + cliente.getCep() + "/json";

			Optional<EnderecoViaCepDTO> enderecoViaCep = Optional
					.ofNullable(restTemplate.getForObject(uri, EnderecoViaCepDTO.class));
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

				Endereco enderecoCadastro = new Endereco(enderecoBanco, cliente);
				Cliente clienteBanco = new Cliente(cliente, enderecoCadastro);
				clienteRepository.save(clienteBanco);
				ClienteDTO2 clienteDTO2 = new ClienteDTO2(clienteRepository.findById(idCliente).get());
				return clienteDTO2;

			} else {
				return null;
			}

		}

	}

	@Transactional
	public void deleteById(Long idCliente) {
		if (!clienteRepository.existsById(idCliente)) {
			throw new NotFoundErroException("Cliente não encontrado!");
		}
		clienteRepository.deleteById(idCliente);
	}

}
