package org.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.domain.Produto;
import org.serratec.dto.ProdutoDTO;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public List<ProdutoDTO> listar(){
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> produtosDTO = new ArrayList<>();
		
		for (Produto produto : produtos) {
			produtosDTO.add(new ProdutoDTO(produto));
		}
		return produtosDTO;
	}
	
	
}
