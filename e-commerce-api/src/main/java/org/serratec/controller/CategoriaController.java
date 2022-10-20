package org.serratec.controller;

import java.util.List;

import org.serratec.domain.Categoria;
import org.serratec.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		return ResponseEntity.ok(categoriaService.listar());
	}
}
