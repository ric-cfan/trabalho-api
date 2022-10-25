package org.serratec.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.serratec.dto.CategoriaDTO;
import org.serratec.dto.CategoriaDTO2;
import org.serratec.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	public ResponseEntity<List<CategoriaDTO>> listarTodos() {
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
	public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long idCategoria) {
		CategoriaDTO categoria = categoriaService.findById(idCategoria);
        return ResponseEntity.ok(categoria); 
            
	}
	
	@PostMapping
	@ApiOperation(value = "Insere os dados de uma Categoria", notes = "Inserir Categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Categoria adicionada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<CategoriaDTO> cadastrar(@Valid @RequestBody CategoriaDTO2 categoria) {
		CategoriaDTO categoriaDTO = categoriaService.save(categoria);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoriaDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(categoriaDTO);
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
	public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long idCategoria, @Valid @RequestBody CategoriaDTO2 categoria) {
		CategoriaDTO categoriaMostrar = categoriaService.atualizar(idCategoria,categoria);
		return ResponseEntity.ok(categoriaMostrar);
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
