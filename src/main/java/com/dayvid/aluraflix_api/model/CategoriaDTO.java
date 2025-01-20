package com.dayvid.aluraflix_api.model;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
		@NotBlank(message = "Campo Título obrigatório") String titulo,
		@NotBlank(message = "Campo Cor obrigatório") String cor) {

	public CategoriaDTO(Categoria categoria) {
		this(categoria.getTitulo(), categoria.getCor());
	}
}
