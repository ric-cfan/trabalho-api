package org.serratec.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.dto.ClienteDTO1;
import org.serratec.dto.ClienteDTO2;
import org.serratec.service.ClienteService;
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
	public ResponseEntity<ClienteDTO2> buscarPorId(@PathVariable Long idCliente) {
		Optional<ClienteDTO2> cliente = Optional.ofNullable(clienteService.findById(idCliente));
		if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get()); 
        }
        return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ApiOperation(value = "Insere os dados de um Cliente", notes = "Inserir Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente adicionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ClienteDTO2> cadastrar(@Valid @RequestBody ClienteDTO1 cliente) {
		ClienteDTO2 clienteDTO2;
		clienteDTO2 = clienteService.cadastrar(cliente);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(clienteDTO2.getIdCliente())
				.toUri();
		return ResponseEntity.created(uri).body(clienteDTO2);
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
	public ResponseEntity<ClienteDTO2> salvar(@PathVariable Long idCliente, @Valid @RequestBody ClienteDTO1 cliente) {
		Optional<ClienteDTO2> clienteDTO2;
		clienteDTO2 = Optional.ofNullable(clienteService.atualizar(cliente, idCliente));
		if(clienteDTO2.isPresent()) {
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{idCliente}")
					.buildAndExpand(clienteDTO2.get().getIdCliente())
					.toUri();
			return ResponseEntity.created(uri).body(clienteDTO2.get());
		}
		return ResponseEntity.notFound().build();
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
		Boolean idExiste = clienteService.deleteById(idCliente);
		if(idExiste == true) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
    }

}
