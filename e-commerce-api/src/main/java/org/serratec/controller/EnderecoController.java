package org.serratec.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.serratec.domain.Endereco;
import org.serratec.dto.EnderecoDTO;
import org.serratec.dto.EnderecoViaCepDTO;
import org.serratec.service.EnderecoService;
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
@RequestMapping("/api/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping(value = "/buscacep/{cep}")
	@ApiOperation(value = "Retorna um Endereço", notes = "Endereço")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um Endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<EnderecoViaCepDTO> buscaPorCep(@PathVariable String cep) {
		EnderecoViaCepDTO endereco = enderecoService.buscar(cep);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(endereco);
		}

	}

	@GetMapping
	@ApiOperation(value = "Retorna lista de Endereços", notes = "Listagem de Endereços")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna lista de Endereços"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<List<EnderecoDTO>> listarTodos() {
		return ResponseEntity.ok(enderecoService.buscaTodos());
	}

	@GetMapping("/{idEndereco}")
	@ApiOperation(value = "Retorna um Endereço", notes = "Endereço")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um Endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Endereco> buscarPorId(@PathVariable Long idEndereco) {
		Optional<Endereco> endereco = enderecoService.findById(idEndereco);
		if (endereco.isPresent()) {
			return ResponseEntity.ok(endereco.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping({ "/cadastrar" })
	@ApiOperation(value = "Insere os dados de um endereço", notes = "Inserir Endereço")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Endereço adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Endereco> cadastrar(@Valid @RequestBody Endereco endereco) {
		endereco = enderecoService.save(endereco);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId())
				.toUri();
		return ResponseEntity.created(uri).body(endereco);
	}

	@PutMapping("/{idEndereco}")
	@ApiOperation(value = "Atualiza dados de um Endereço", notes = "Atualizar Endereço")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Endereço Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Endereco> salvar(@PathVariable Long idEndereco, @Valid @RequestBody Endereco endereco) {
		endereco.setId(idEndereco);
		endereco = enderecoService.save(endereco);
		return ResponseEntity.ok(endereco);
	}

	@DeleteMapping("/{idEndereco}")
	@ApiOperation(value = "Remove um Endereço", notes = "Remover Endereço")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Endereço Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> deletar(@PathVariable Long idEndereco) {
		enderecoService.deleteById(idEndereco);
		return ResponseEntity.noContent().build();
	}
}
