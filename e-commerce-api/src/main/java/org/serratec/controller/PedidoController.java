package org.serratec.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.serratec.dto.PedidoDTO;
import org.serratec.dto.PedidoDTO2;
import org.serratec.dto.RelatorioPedidoDTO;
import org.serratec.exception.DataPedidoAnteriorException;
import org.serratec.service.PedidoService;
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
	public ResponseEntity<List<PedidoDTO>> listarTodos() {
		return ResponseEntity.ok(pedidoService.listarTodos());
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
	public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long idPedido) {
		PedidoDTO pedido = pedidoService.findById(idPedido);
		return ResponseEntity.ok(pedido);
	}
	
	@GetMapping("/relatorio")
	@ApiOperation(value = "Retorna lista de Pedidos", notes = "Listagem de Pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de Pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<RelatorioPedidoDTO>> listarTodosRelatorios() {
		return ResponseEntity.ok(pedidoService.listarTodosRelatorios());
	}

	@GetMapping("/{idPedido}/relatorio")
	@ApiOperation(value = "Retorna um Pedido", notes = "Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um Pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<RelatorioPedidoDTO> buscarPorIdRelatorio(@PathVariable Long idPedido) {
		RelatorioPedidoDTO pedido = pedidoService.findRelatorioById(idPedido);
		return ResponseEntity.ok(pedido);
	}

	@PostMapping
	@ApiOperation(value = "Insere os dados de um pedido", notes = "Inserir Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Pedido adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<PedidoDTO> cadastrar(@Valid @RequestBody PedidoDTO2 pedido)
			throws DataPedidoAnteriorException {
		PedidoDTO pedidoDTO = pedidoService.cadastrar(pedido);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(pedidoDTO.getIdPedido())
				.toUri();
		return ResponseEntity.created(uri).body(pedidoDTO);
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
	public ResponseEntity<PedidoDTO> salvar(@PathVariable Long idPedido, @Valid @RequestBody PedidoDTO2 pedido) {
		PedidoDTO pedidoDTO = pedidoService.atualizar(pedido, idPedido);
		return ResponseEntity.ok(pedidoDTO);
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
