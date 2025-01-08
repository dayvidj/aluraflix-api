package com.dayvid.aluraflix_api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VideoDTO(
		@NotBlank String titulo, 
		@NotBlank String descricao, 
		@NotBlank String url) {

	public VideoDTO(Video video) {
		this(video.getTitulo(), video.getDescricao(), video.getUrl());
	};
}
