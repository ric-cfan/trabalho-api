package org.serratec.service;

import java.util.Optional;

import org.serratec.domain.Perfil;
import org.serratec.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {
	@Autowired
	private PerfilRepository perfilRepository;

	public Perfil buscar(Long id) {
		Optional<Perfil> perfil = perfilRepository.findById(id);
		return perfil.get();
	}

}
