package org.serratec.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.serratec.domain.Cliente;
import org.serratec.dto.ClienteDTO2;
import org.serratec.service.ClienteService;
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
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	@ApiOperation(value = "Retorna lista de Clientes", notes = "Listagem de Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de Clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ClienteDTO2>> buscaTodos() {
		List<ClienteDTO2> clientes = clienteService.buscaTodos();
		if (clientes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping("/{idCliente}")
	@ApiOperation(value = "Retorna uma Cliente", notes = "Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um Cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long idCliente) {
		Optional<Cliente> cliente = clienteService.findById(idCliente);
		if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get()); 
            }
            
            return ResponseEntity.notFound().build();
	}
	
	@PostMapping({"/cadastrar"})
	@ApiOperation(value = "Insere os dados de um Cliente", notes = "Inserir Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente adicionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente) {
		cliente = clienteService.save(cliente);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@PutMapping("/{idCliente}")
	@ApiOperation(value = "Atualiza dados de um Cliente", notes = "Atualizar Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Cliente> salvar(@PathVariable Long idCliente, @Valid @RequestBody Cliente cliente) {
		cliente.setId(idCliente);
		cliente = clienteService.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{idCliente}")
	@ApiOperation(value = "Remove um Cliente", notes = "Remover Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
    public ResponseEntity<Void> deletar(@PathVariable Long idCliente) {
		clienteService.deleteById(idCliente);
		return ResponseEntity.noContent().build();
    }

}
