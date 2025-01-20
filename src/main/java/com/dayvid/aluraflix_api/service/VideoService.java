package com.dayvid.aluraflix_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public VideoDTO salvarVideo(VideoDTO video) {
		var categoria = categoriaRepository.getReferenceById(Optional.ofNullable(video.categoriaId()).orElse(1L));

		videoRepository.save(new Video(video, categoria));
		return video;
	}

	@Transactional(readOnly = true)
	public List<DadosVideoDTO> listarTodosVideos() {
		return videoRepository.findAll().stream().map(DadosVideoDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public VideoDTO buscarVideo(Long id) {
		if (!videoRepository.existsById(id)) {
			throw new ObjetoNaoEncontadoException("Video não encontrado");
		}
		return new VideoDTO(videoRepository.getReferenceById(id));
	}

	@Transactional
	public VideoDTO atualizarVideo(DadosVideoDTO dadosVideo) {
		if (!videoRepository.existsById(dadosVideo.id())) {
			throw new ObjetoNaoEncontadoException("Video não encontrado");
		}

		var video = videoRepository.getReferenceById(dadosVideo.id());
		video.atualizarDados(dadosVideo);

		return new VideoDTO(video);
	}

	@Transactional
	public String deletarVideo(Long id) {
		if (!videoRepository.existsById(id)) {
			throw new ObjetoNaoEncontadoException("Video não encontrado");
		}
		videoRepository.deleteById(id);
		return "Video deletado com sucesso.";
	}

}
