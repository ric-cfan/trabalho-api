package org.serratec.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.domain.Imagem;
import org.serratec.dto.ImagemProdutoDTO;
import org.serratec.dto.ProdutoDTO2;
import org.serratec.service.ImagemService;
import org.serratec.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ImagemService imagemService;
	
	@GetMapping
	@ApiOperation(value = "Retorna lista de Produtos", notes = "Listagem de Produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de Produtos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ImagemProdutoDTO>> listar() {
		return ResponseEntity.ok(produtoService.listar());
	}

    @GetMapping("/{idProduto}")
	@ApiOperation(value = "Retorna um Produto", notes = "Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um Produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ImagemProdutoDTO> buscarPorId(@PathVariable Long idProduto) {
		ImagemProdutoDTO produto = produtoService.findById(idProduto);
            return ResponseEntity.ok(produto);
	}
	
    @GetMapping("/{idProduto}/imagem")
	@ApiOperation(value = "Retorna um Produto", notes = "Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um Produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<byte[]> imagemPorId(@PathVariable Long idProduto) {
		Optional<Imagem> imagem = imagemService.buscarPorIdProduto(idProduto);
		if (imagem.isPresent()) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", imagem.get().getTipo());
			headers.add("Content-length", String.valueOf(imagem.get().getDados().length));
			return new ResponseEntity<byte[]>(imagem.get().getDados(), headers, HttpStatus.OK);
            }
            
            return ResponseEntity.notFound().build();
	}
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ApiOperation(value = "Insere os dados de um produto", notes = "Inserir Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Produto adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ImagemProdutoDTO> cadastrar(@Valid @RequestPart ProdutoDTO2 produto,@RequestPart MultipartFile file) throws IOException {
		ImagemProdutoDTO produtoDTO = produtoService.inserir(produto,file);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produtoDTO.getIdProduto())	
				.toUri();
		return ResponseEntity.created(uri).body(produtoDTO);
	}
	
	@PutMapping(path = "/{idProduto}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ApiOperation(value = "Atualiza dados de um Produto", notes = "Atualizar Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Produto Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ImagemProdutoDTO> salvar(@PathVariable Long idProduto, @Valid @RequestPart ProdutoDTO2 produto,@RequestPart MultipartFile file) throws IOException {
		ImagemProdutoDTO produtoMostrar = produtoService.atualizar(idProduto, produto, file);
		return ResponseEntity.ok(produtoMostrar);
	}
	
	@DeleteMapping("/{idProduto}")
	@ApiOperation(value = "Remove um Produto", notes = "Remover Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Produto Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
    public ResponseEntity<Void> deletar(@PathVariable Long idProduto) {
        produtoService.deleteById(idProduto);
		return ResponseEntity.noContent().build();
    }

}
