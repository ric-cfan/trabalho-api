package org.serratec.controller;

import java.util.List;

import org.serratec.domain.Pedido;
import org.serratec.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listar() {
		return ResponseEntity.ok(pedidoService.listar());
	}
}
