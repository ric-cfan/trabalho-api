package org.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.serratec.domain.Categoria;
import org.serratec.domain.Imagem;
import org.serratec.domain.Produto;
import org.serratec.dto.ImagemProdutoDTO;
import org.serratec.dto.ProdutoDTO2;
import org.serratec.exception.DescricaoProdutoException;
import org.serratec.exception.NotFoundErroException;
import org.serratec.repository.CategoriaRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<ImagemProdutoDTO> listar() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ImagemProdutoDTO> produtosDTO = new ArrayList<>();
		for (Produto produto : produtos) {
			URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/produto/{id}/imagem")
					.buildAndExpand(produto.getId())
					.toUri();
			String url = uri.toString();
			produtosDTO.add(new ImagemProdutoDTO(produto, url));
		}
		return produtosDTO;
	}

	public ImagemProdutoDTO findById(Long idProduto) {
		if (produtoRepository.findById(idProduto).isPresent()) {

			ImagemProdutoDTO dto = new ImagemProdutoDTO(produtoRepository.findById(idProduto).get());

			if (Optional.ofNullable(dto).isPresent()) {
				URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/produto/{id}/imagem")
						.buildAndExpand(dto.getIdProduto())
						.toUri();
				dto.setUrlImagem(uri.toString());
			}

			return dto;
		}
		throw new NotFoundErroException("Produto não encontrado!");

	}

	@Transactional
	public ImagemProdutoDTO inserir(ProdutoDTO2 produto, MultipartFile file)
			throws IOException, DescricaoProdutoException {
		Produto descricaoValida = produtoRepository.findByDescricao(produto.getDescricao());

		if (descricaoValida != null) {
			throw new DescricaoProdutoException("Já existe um produto com está descrição " + produto.getDescricao());
		}
		Categoria categoria = categoriaRepository.findById(produto.getIdCategoria()).get();
		Produto produtoBanco = new Produto(produto, categoria);

		Imagem imagem = new Imagem();
		imagem.setTipo(file.getContentType());
		imagem.setDados(file.getBytes());

		imagem.setProduto(produtoBanco);
		produtoBanco.setImagem(imagem);
		produtoBanco = produtoRepository.saveAndFlush(produtoBanco);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/produto/{id}/imagem")
				.buildAndExpand(imagem.getProduto().getId())
				.toUri();
		ImagemProdutoDTO dto = new ImagemProdutoDTO(produtoBanco);
		dto.setUrlImagem(uri.toString());

		return dto;
	}

	@Transactional
	public ImagemProdutoDTO atualizar(Long idProduto, ProdutoDTO2 produto, MultipartFile file) throws IOException {
		if(!produtoRepository.existsById(idProduto)) {
			throw new NotFoundErroException("Produto não encontrado!");
		}
		Optional<Categoria> categoria = categoriaRepository.findById(produto.getIdCategoria());
		Produto produtoAtualizado = new Produto(produto, categoria.get());
		Produto produtoSalvo = produtoRepository.findById(idProduto).get();
		if (Optional.ofNullable(produtoSalvo).isPresent()) {
			produtoAtualizado.setId(idProduto);
		} else {
			throw new NotFoundErroException("Produto não encontrado!");
		}

		Imagem imagem = new Imagem();
		imagem.setTipo(file.getContentType());
		imagem.setProduto(produtoAtualizado);
		imagem.setDados(file.getBytes());

		if (Optional.ofNullable(produtoSalvo.getImagem()).isPresent()) {
			imagem.setId(Optional.ofNullable(produtoSalvo.getImagem().getId()).get());
		}

		produtoAtualizado.setImagem(imagem);
		produtoAtualizado = produtoRepository.save(produtoAtualizado);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/produto/{id}/imagem")
				.buildAndExpand(produtoAtualizado.getId())
				.toUri();
		ImagemProdutoDTO dto = new ImagemProdutoDTO(produtoAtualizado);
		dto.setUrlImagem(uri.toString());

		return dto;
	}

	@Transactional
	public void deleteById(Long idProduto) {
		if (!produtoRepository.existsById(idProduto)) {
			throw new NotFoundErroException("Produto não encontrado!");
		}
		produtoRepository.deleteById(idProduto);

	}

}
