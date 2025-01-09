package com.dayvid.aluraflix_api.model;

import jakarta.validation.constraints.NotNull;

public record DadosVideoDTO(
		@NotNull(message = "ID não pode ser nulo") 
		Long id, 
		String titulo, 
		String descricao, 
		String url) {
	
	public DadosVideoDTO(Video video) {
		this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl());
	}
}
