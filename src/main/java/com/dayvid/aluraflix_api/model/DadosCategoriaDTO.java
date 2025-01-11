package com.dayvid.aluraflix_api.model;

import jakarta.validation.constraints.NotNull;

public record DadosCategoriaDTO(
		@NotNull(message = "ID não pode ser nulo") 
		Long id, 
		String titulo, 
		String cor) {

	public DadosCategoriaDTO(Categoria categoria) {
		this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
	}
	
}
