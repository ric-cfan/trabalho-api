package org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.domain.Endereco;
import org.serratec.dto.EnderecoDTO;
import org.serratec.dto.EnderecoViaCepDTO;
//import org.serratec.repository.ClienteRepository;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

//	@Autowired
//	private ClienteRepository clienteRepository;
	
	public List<EnderecoDTO> buscaTodos() {
		List<Endereco> enderecos = enderecoRepository.findAll();
		List<EnderecoDTO> enderecoDTO = new ArrayList<>();

		for (Endereco endereco : enderecos) {
			enderecoDTO.add(new EnderecoDTO(endereco));
		}

		return enderecoDTO;
	}

	public Optional<Endereco> findById(Long idEndereco) {
		return enderecoRepository.findById(idEndereco);
	}

	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public void deleteById(Long idEndereco) {
		enderecoRepository.deleteById(idEndereco);
	}
	
	public EnderecoViaCepDTO buscar(String cep) {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return new EnderecoViaCepDTO(endereco.get());
		} else {

			RestTemplate restTemplate = new RestTemplate();

			String uri = "http://viacep.com.br/ws/" + cep + "/json";

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
				return enderecoBanco;
			} else {
				return null;
			}
		}
	}

//	private EnderecoViaCepDTO inserir(Endereco endereco) {
//		return new EnderecoViaCepDTO(enderecoRepository.save(endereco));
//	}
}
