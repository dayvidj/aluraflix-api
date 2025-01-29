package com.dayvid.aluraflix_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dayvid.aluraflix_api.model.CategoriaDTO;
import com.dayvid.aluraflix_api.model.DadosCategoriaDTO;
import com.dayvid.aluraflix_api.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@PostMapping
	public ResponseEntity<DadosCategoriaDTO> criarCategoria(@RequestBody @Valid CategoriaDTO dadosCategoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarCategoria(dadosCategoria));
	}

	@GetMapping
	public ResponseEntity<Page<DadosCategoriaDTO>> listarCategorias(@PageableDefault(size = 5) Pageable pageable) {
		return ResponseEntity.ok(service.listarTodasCategorias(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosCategoriaDTO> exibirCategoriaPorID(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscarCategoria(id));
	}

	@PutMapping
	public ResponseEntity<CategoriaDTO> updateCategoria(@RequestBody @Valid DadosCategoriaDTO dadosCategoria) {
		return ResponseEntity.ok(service.atualizarCategoria(dadosCategoria));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {
		return ResponseEntity.ok(service.deletarCategoria(id));
	}

}
