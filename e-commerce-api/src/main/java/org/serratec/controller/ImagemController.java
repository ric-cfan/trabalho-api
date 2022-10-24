package org.serratec.controller;

import org.serratec.domain.Imagem;
import org.serratec.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imagem")
public class ImagemController {

	@Autowired
	private ImagemService imagemService;
//
//	@PutMapping(path = "/{id}/produto/{idProduto}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<byte[]> inserir(@PathVariable Long id, @RequestPart MultipartFile file, @PathVariable Long idProduto) throws IOException {
//		Imagem imagem = imagemService.atualizar(id, file, idProduto);
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-type", imagem.getTipo());
//		headers.add("Content-length", String.valueOf(imagem.getDados().length));
//		return new ResponseEntity<>(imagem.getDados(), headers, HttpStatus.OK);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> buscarImagem(@PathVariable Long id) {
		Imagem imagem = imagemService.buscarPorId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", imagem.getTipo());
		headers.add("Content-length", String.valueOf(imagem.getDados().length));
		return new ResponseEntity<>(imagem.getDados(), headers, HttpStatus.OK);
	}


}
