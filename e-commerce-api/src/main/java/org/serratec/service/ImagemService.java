package org.serratec.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.domain.Imagem;
import org.serratec.domain.Produto;
import org.serratec.exception.NotFoundErroException;
import org.serratec.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagemService {

	@Autowired
	private ImagemRepository imagemRepository;
	

	@Transactional
	public Imagem buscarPorId(Long id) {
		Optional<Imagem> imagem = imagemRepository.findById(id);
		if (imagem.isPresent()) {
			return imagem.get();
		}
		throw new NotFoundErroException("Imagem não encontrada!");
	}

//	@Transactional
//	public Imagem atualizar(Long id, MultipartFile file, Long idProduto) throws IOException {
//		Imagem imagem = new Imagem();
//		imagem.setId(id);
//		imagem.setTipo(file.getContentType());
//		imagem.setDados(file.getBytes());
//
//		return imagemRepository.save(imagem);
//	}

	@Transactional
	public Optional<Imagem> buscarPorIdProduto(Long id) {
		Produto produto = new Produto();
		produto.setId(id);
		Optional<Imagem> imagem = imagemRepository.findByProduto(produto);
		if (!imagem.isPresent()) {
			throw new NotFoundErroException("Imagem não encontrada!");
		}
		return imagem;
	}
}
