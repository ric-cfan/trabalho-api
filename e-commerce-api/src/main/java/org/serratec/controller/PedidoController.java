package org.serratec.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.domain.Pedido;
import org.serratec.domain.Produto;
import org.serratec.dto.PedidoDTO;
import org.serratec.dto.ProdutoDTO;
import org.serratec.service.PedidoService;
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
@RequestMapping("/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	@ApiOperation(value = "Retorna lista de Pedidos", notes = "Listagem de Pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de Pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<PedidoDTO>> listar() {
		return ResponseEntity.ok(pedidoService.listar());
	}

    @GetMapping("/{idPedido}")
	@ApiOperation(value = "Retorna um Pedido", notes = "Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um Pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long idPedido) {
		Optional<Pedido> pedido = pedidoService.findById(idPedido);
		if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get()); 
            }
            
            return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping({"/cadastrar"})
	@ApiOperation(value = "Insere os dados de um pedido", notes = "Inserir Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Pedido adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Pedido> cadastrar(@Valid @RequestBody Pedido pedido) {
		pedido = pedidoService.save(pedido);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(pedido.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pedido);
	}

	@PutMapping("/{idPedido}")
	@ApiOperation(value = "Atualiza dados de um Pedido", notes = "Atualizar Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Pedido Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<Pedido> salvar(@PathVariable Long idPedido, @Valid @RequestBody Pedido pedido) {
		pedido.setId(idPedido);
		pedido = pedidoService.save(pedido);
		return ResponseEntity.ok(pedido);
	}

	@DeleteMapping("/{idPedido}")
	@ApiOperation(value = "Remove um Pedido", notes = "Remover Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Pedido Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
    public ResponseEntity<Void> deletar(@PathVariable Long idPedido) {
        pedidoService.deleteById(idPedido);
		return ResponseEntity.noContent().build();
    }
}
