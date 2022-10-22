package org.serratec.service;

import java.util.List;
import java.util.Optional;

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
	
    public Optional<Categoria> findById(Long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }
}
