package com.dayvid.aluraflix_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.dayvid.aluraflix_api.model.DadosVideoDTO;
import com.dayvid.aluraflix_api.model.VideoDTO;
import com.dayvid.aluraflix_api.service.VideoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoService service;

	@PostMapping
	public ResponseEntity<VideoDTO> criarVideo(@RequestBody @Valid VideoDTO dadosVideo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarVideo(dadosVideo));
	}

	@GetMapping
	public ResponseEntity<List<DadosVideoDTO>> listarVideos() {
		return ResponseEntity.ok(service.listarTodosVideos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideoDTO> exibirVideoPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscarVideo(id));
	}

	@PutMapping
	public ResponseEntity<VideoDTO> updateVideo(@RequestBody @Valid DadosVideoDTO dadosVideo) {
		return ResponseEntity.ok(service.atualizarVideo(dadosVideo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
		return ResponseEntity.ok(service.deletarVideo(id));
	}

}
