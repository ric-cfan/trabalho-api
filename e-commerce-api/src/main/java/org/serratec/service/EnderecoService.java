package org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.serratec.domain.Endereco;
import org.serratec.dto.EnderecoDTO;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
}
