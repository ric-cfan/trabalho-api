package org.serratec.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.serratec.domain.Produto;
import org.serratec.dto.ProdutoDTO;
import org.serratec.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	@ApiOperation(value = "Retorna lista de Produtos", notes = "Listagem de Produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de Produtos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ProdutoDTO>> listar() {
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
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long idProduto) {
		Optional<Produto> produto = produtoService.findById(idProduto);
		if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get()); 
            }
            
            return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping({"/cadastrar"})
	@ApiOperation(value = "Insere os dados de um produto", notes = "Inserir Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Produto adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto) {
		produto = produtoService.save(produto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@PutMapping("/{idProduto}")
	@ApiOperation(value = "Atualiza dados de um Produto", notes = "Atualizar Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Produto Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Produto> salvar(@PathVariable Long idProduto, @Valid @RequestBody Produto produto) {
		produto.setId(idProduto);
		produto = produtoService.save(produto);
		return ResponseEntity.ok(produto);
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
