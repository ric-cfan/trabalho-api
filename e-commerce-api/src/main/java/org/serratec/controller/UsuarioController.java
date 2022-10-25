package org.serratec.controller;

import java.net.URI;
import java.util.List;

import org.serratec.domain.Usuario;
import org.serratec.dto.UsuarioDTO;
import org.serratec.dto.UsuarioInserirDTO;
import org.serratec.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@PostMapping
	public ResponseEntity<UsuarioInserirDTO> inserir(@RequestBody UsuarioInserirDTO usuario,Usuario idUsuario) {
		UsuarioDTO usuarioCadastro = usuarioService.inserir(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(idUsuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuario);
	}

}
