package com.dayvid.aluraflix_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "categorias")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String cor;

	public Categoria(CategoriaDTO dados) {
		this.titulo = dados.titulo();
		this.cor = dados.cor();
	}

	public void atualizarDados(DadosCategoriaDTO dados) {
		if (dados.titulo() != null)
			this.titulo = dados.titulo();
		if (dados.cor() != null)
			this.cor = dados.cor();
	}

}
