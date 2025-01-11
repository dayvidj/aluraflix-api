package com.dayvid.aluraflix_api.model;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(@NotBlank String titulo, @NotBlank String cor) {

	public CategoriaDTO(Categoria categoria) {
		this(categoria.getTitulo(), categoria.getCor());
	}
}
