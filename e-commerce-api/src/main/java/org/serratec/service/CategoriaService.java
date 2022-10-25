package org.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.domain.Categoria;
import org.serratec.dto.CategoriaDTO;
import org.serratec.dto.CategoriaDTO2;
import org.serratec.exception.NotFoundErroException;
import org.serratec.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<CategoriaDTO> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaDTO> categoriasDTO = new ArrayList<>();
		for (Categoria categoria : categorias) {
			categoriasDTO.add(new CategoriaDTO(categoria));
		}
		return categoriasDTO;
	}

	public CategoriaDTO findById(Long idCategoria) {
		if (categoriaRepository.findById(idCategoria).isPresent()) {
			CategoriaDTO categoriaDTO = new CategoriaDTO(categoriaRepository.findById(idCategoria).get());
			return categoriaDTO;
		}
			throw new NotFoundErroException("Categoria não encontrada!");
	}

	@Transactional
	public CategoriaDTO save(CategoriaDTO2 categoria) {
		Categoria categoriaBanco = new Categoria(categoria);
		categoriaRepository.save(categoriaBanco);
		CategoriaDTO categoriaDTO = new CategoriaDTO(categoriaRepository.findById(categoriaBanco.getId()).get());
		return categoriaDTO;
	}

	@Transactional
	public CategoriaDTO atualizar(Long idCategoria, CategoriaDTO2 categoria) {
		if (categoriaRepository.existsById(idCategoria)) {
			Categoria categoriaBanco = new Categoria(categoria, idCategoria);
			categoriaRepository.save(categoriaBanco);
			CategoriaDTO categoriaDTO = new CategoriaDTO(categoriaRepository.findById(categoriaBanco.getId()).get());
			return categoriaDTO;
		}
		throw new NotFoundErroException("Categoria não encontrada!");
	}

	@Transactional
	public void deleteById(Long idCategoria) {
		if (!categoriaRepository.existsById(idCategoria)) {
			throw new NotFoundErroException("Categoria não encontrada!");
		}
		categoriaRepository.deleteById(idCategoria);
	}
}
