package com.dayvid.aluraflix_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayvid.aluraflix_api.exception.ObjetoNaoEncontadoException;
import com.dayvid.aluraflix_api.model.DadosVideoDTO;
import com.dayvid.aluraflix_api.model.Video;
import com.dayvid.aluraflix_api.model.VideoDTO;
import com.dayvid.aluraflix_api.repository.CategoriaRepository;
import com.dayvid.aluraflix_api.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional
	public DadosVideoDTO salvarVideo(VideoDTO dadosVideo) {
		var categoria = categoriaRepository.getReferenceById(Optional.ofNullable(dadosVideo.categoriaId()).orElse(1L));
		var video = new Video(dadosVideo, categoria);

		videoRepository.save(video);
		return new DadosVideoDTO(video);
	}
	
	@Transactional(readOnly = true)
	public Page<DadosVideoDTO> listarTodosVideos(Pageable pageable) {
		return videoRepository.findAll(pageable).map(DadosVideoDTO::new);
	}

	@Transactional(readOnly = true)
	public VideoDTO buscarVideo(Long id) {
		var video = videoRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontadoException("Video não encontrado"));

		return new VideoDTO(video);
	}

	@Transactional(readOnly = true)
	public VideoDTO buscarVideoPorTitulo(String titulo) {
		var video = videoRepository.findByTitulo(titulo);
		return new VideoDTO(video);
	}
	
	@Transactional(readOnly = true)
	public Page<VideoDTO> buscarVideoPorCategoria(Long id, Pageable pageable) {
		var videos = videoRepository.findByCategoriaId(id, pageable);
		return videos.map(VideoDTO::new);
	}

	@Transactional
	public VideoDTO atualizarVideo(DadosVideoDTO dadosVideo) {
		var video = videoRepository.findById(dadosVideo.id())
				.orElseThrow(() -> new ObjetoNaoEncontadoException("Video não encontrado"));

		video.atualizarDados(dadosVideo);
		return new VideoDTO(video);
	}

	@Transactional
	public String deletarVideo(Long id) {
		videoRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontadoException("Video não encontrado com ID " + id));

		videoRepository.deleteById(id);
		return "Video deletado com sucesso.";
	}


}
