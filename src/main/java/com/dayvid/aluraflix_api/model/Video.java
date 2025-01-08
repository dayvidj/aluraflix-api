package com.dayvid.aluraflix_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "videos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private String url;

	public Video(VideoDTO video) {
		this.descricao = video.descricao();
		this.titulo = video.titulo();
		this.url = video.url();
	}

	public void atualizarDados(DadosVideoDTO dados) {
		if (dados.titulo() != null)
			this.titulo = dados.titulo();
		if (dados.descricao() != null)
			this.descricao = dados.descricao();
		if (dados.url() != null)
			this.url = dados.url();
	}

}
