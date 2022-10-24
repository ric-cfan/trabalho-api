package org.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.domain.Categoria;
import org.serratec.dto.CategoriaDTO;
import org.serratec.dto.CategoriaDTO2;
import org.serratec.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    	if(categoriaRepository.findById(idCategoria).isPresent()) {
    		CategoriaDTO categoriaDTO = new CategoriaDTO(categoriaRepository.findById(idCategoria).get());
    		return categoriaDTO;
    	}
    	else {
    		return null;
    	}
    }

    public CategoriaDTO save(CategoriaDTO2 categoria) {
    	Categoria categoriaBanco = new Categoria(categoria);
    	categoriaRepository.save(categoriaBanco);
    	CategoriaDTO categoriaDTO = new CategoriaDTO(categoriaRepository.findById(categoriaBanco.getId()).get());
    	return categoriaDTO;
    }
    
    public CategoriaDTO salvar(Long idCategoria,CategoriaDTO2 categoria) {
    	Categoria categoriaBanco = new Categoria(categoria,idCategoria);
    	categoriaRepository.save(categoriaBanco);
    	CategoriaDTO categoriaDTO = new CategoriaDTO(categoriaRepository.findById(categoriaBanco.getId()).get());
    	return categoriaDTO;
    }
    public void deleteById(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }
}
