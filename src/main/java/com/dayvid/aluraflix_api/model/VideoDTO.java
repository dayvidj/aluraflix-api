package com.dayvid.aluraflix_api.model;

import jakarta.validation.constraints.NotBlank;

public record VideoDTO(
		@NotBlank String titulo, 
		@NotBlank String descricao, 
		@NotBlank String url,
		Long categoriaId) {

	public VideoDTO(Video video) {
		this(video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoria().getId());
	}
}
