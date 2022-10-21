package org.serratec.controller;

import java.util.List;

import org.serratec.dto.ClienteDTO;
import org.serratec.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscaTodos() {
		List<ClienteDTO> clientes = clienteService.buscaTodos();
		if (clientes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientes);
	}
}
