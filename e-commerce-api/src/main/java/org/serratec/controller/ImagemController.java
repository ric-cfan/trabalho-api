package org.serratec.controller;

import java.io.IOException;

import org.serratec.domain.Imagem;
import org.serratec.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/imagem")
public class ImagemController {

	@Autowired
	private ImagemService imagemService;

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public Imagem inserir(@RequestPart MultipartFile file) throws IOException {
		return imagemService.inserir(file);
	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> buscarImagem(@PathVariable Long id) {
		Imagem imagem = imagemService.buscarPorId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", imagem.getTipo());
		headers.add("Content-length", String.valueOf(imagem.getDados().length));
		return new ResponseEntity<>(imagem.getDados(), headers, HttpStatus.OK);
	}


}
