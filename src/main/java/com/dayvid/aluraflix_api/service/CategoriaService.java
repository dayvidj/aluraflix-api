package com.dayvid.aluraflix_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayvid.aluraflix_api.exception.ObjetoDuplicadoException;
import com.dayvid.aluraflix_api.exception.ObjetoNaoEncontadoException;
import com.dayvid.aluraflix_api.model.Categoria;
import com.dayvid.aluraflix_api.model.CategoriaDTO;
import com.dayvid.aluraflix_api.model.DadosCategoriaDTO;
import com.dayvid.aluraflix_api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional
	public DadosCategoriaDTO salvarCategoria(CategoriaDTO dados) {

		if (repository.existsByTituloIgnoreCase(dados.titulo())) {
			throw new ObjetoDuplicadoException("Esta categoria já existe");
		}

		var categoria = new Categoria(dados);
		repository.save(categoria);
		return new DadosCategoriaDTO(categoria);
	}

	@Transactional(readOnly = true)
	public Page<DadosCategoriaDTO> listarTodasCategorias(Pageable pageable) {
		var categorias = repository.findAll(pageable).map(DadosCategoriaDTO::new);
		return categorias;
	}

	@Transactional(readOnly = true)
	public DadosCategoriaDTO buscarCategoria(Long id) {
		if (!repository.existsById(id)) {
			throw new ObjetoNaoEncontadoException("Categoria não encontrada");
		}
		return new DadosCategoriaDTO(repository.getReferenceById(id));
	}

	@Transactional
	public CategoriaDTO atualizarCategoria(DadosCategoriaDTO dadosCategoria) {
		if (!repository.existsById(dadosCategoria.id())) {
			throw new ObjetoNaoEncontadoException("Categoria não encontrada com ID: " + dadosCategoria.id());
		}

		var categoria = repository.getReferenceById(dadosCategoria.id());
		categoria.atualizarDados(dadosCategoria);

		return new CategoriaDTO(categoria);
	}

	@Transactional
	public String deletarCategoria(Long id) {
		if (!repository.existsById(id)) {
			throw new ObjetoNaoEncontadoException("Falha: Categoria não encontrada com ID " + id);
		}
		repository.deleteById(id);
		return "Categoria deletada com sucesso!";
	}

}
