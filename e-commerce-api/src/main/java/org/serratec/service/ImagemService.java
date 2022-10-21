package org.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.domain.Imagem;
import org.serratec.domain.Produto;
import org.serratec.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		return null;
	}

	@Transactional
	public Imagem inserir(MultipartFile file) throws IOException {
		Imagem imagem = new Imagem();
		imagem.setTipo(file.getContentType());
		imagem.setDados(file.getBytes());
		URI uri = ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/{id}")
			.buildAndExpand(imagem.getId())
			.toUri();
		imagem.setUrl(uri.toString());
		imagem.setProduto(new Produto());
		imagem.getProduto().setId(1L);
		return imagemRepository.save(imagem);
	}
}
