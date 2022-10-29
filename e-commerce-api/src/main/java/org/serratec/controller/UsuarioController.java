package org.serratec.controller;

import java.net.URI;
import java.util.List;

import org.serratec.dto.UsuarioDTO;
import org.serratec.dto.UsuarioInserirDTO;
import org.serratec.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	@ApiOperation(value = "Retorna lista de Usuários", notes = "Listagem de Usuários")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna lista de Usuários"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@PostMapping
	@ApiOperation(value = "Insere os dados de um Usuário", notes = "Inserir Usuário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuário adicionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuario) {
		UsuarioDTO usuarioCadastro = usuarioService.inserir(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCadastro.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioCadastro);
	}

	@GetMapping("/{idUsuario}")
	@ApiOperation(value = "Retorna uma Usuário", notes = "Usuário")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma Categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long idUsuario) {
		UsuarioDTO usuario = usuarioService.findById(idUsuario);
		return ResponseEntity.ok(usuario);

	}

}
