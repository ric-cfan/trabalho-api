package org.serratec.service;

import java.util.List;

import org.serratec.domain.Categoria;
import org.serratec.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
}
