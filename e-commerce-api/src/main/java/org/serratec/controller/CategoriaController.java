package org.serratec.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.serratec.domain.Categoria;
import org.serratec.service.CategoriaService;
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
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	@ApiOperation(value = "Retorna lista de Categorias", notes = "Listagem de Categorias")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de Categorias"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<Categoria>> listarTodos() {
		return ResponseEntity.ok(categoriaService.listar());
	}
	
	@GetMapping("/{idCategoria}")
	@ApiOperation(value = "Retorna uma Categoria", notes = "Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma Categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long idCategoria) {
		Optional<Categoria> categoria = categoriaService.findById(idCategoria);
		if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get()); 
            }
            
            return ResponseEntity.notFound().build();
	}
	
	@PostMapping({"/cadastrar"})
	@ApiOperation(value = "Insere os dados de uma Categoria", notes = "Inserir Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Categoria adicionada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody Categoria categoria) {
		categoria = categoriaService.save(categoria);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).body(categoria);
	}
	
	@PutMapping("/{idCategoria}")
	@ApiOperation(value = "Atualiza dados de uma Categoria", notes = "Atualizar Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria Atualizada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Categoria> salvar(@PathVariable Long idCategoria, @Valid @RequestBody Categoria categoria) {
		categoria.setId(idCategoria);
		categoria = categoriaService.save(categoria);
		return ResponseEntity.ok(categoria);
	}
	
	@DeleteMapping("/{idCategoria}")
	@ApiOperation(value = "Remove uma Categoria", notes = "Remover Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Categoria Removida"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
    public ResponseEntity<Void> deletar(@PathVariable Long idCategoria) {
        categoriaService.deleteById(idCategoria);
		return ResponseEntity.noContent().build();
    }
}
