package org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.domain.Categoria;
import org.serratec.domain.Produto;
import org.serratec.dto.ProdutoDTO;
import org.serratec.dto.ProdutoDTO2;
import org.serratec.repository.CategoriaRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public List<ProdutoDTO> listar(){
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> produtosDTO = new ArrayList<>();
		
		for (Produto produto : produtos) {
			produtosDTO.add(new ProdutoDTO(produto));
		}
		return produtosDTO;
	}
	
	public Optional<Produto> findById(Long idProduto) {
        return produtoRepository.findById(idProduto);
    }

    public ProdutoDTO save(ProdutoDTO2 produto) {
       Categoria categoria = categoriaRepository.findById(produto.getIdCategoria()).get();
       Produto produtoBanco = new Produto(produto,categoria);
       produtoRepository.save(produtoBanco);
       ProdutoDTO produtoDTO = new ProdutoDTO(produtoRepository.findById(produtoBanco.getId()).get());
       return produtoDTO;
    }

    public ProdutoDTO salvar(Long idProduto,ProdutoDTO2 produto) {
    	Categoria categoria = categoriaRepository.findById(produto.getIdCategoria()).get();
    	Produto produtoBanco = new Produto(produto,categoria);
    	produtoRepository.save(produtoBanco);
    	ProdutoDTO produtoDTO = new ProdutoDTO(produtoRepository.findById(produtoBanco.getId()).get());
    	return produtoDTO;
    }
    public void deleteById(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }
	
}
