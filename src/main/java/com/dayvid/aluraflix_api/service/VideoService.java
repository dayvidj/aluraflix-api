package com.dayvid.aluraflix_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayvid.aluraflix_api.exception.ObjetoNaoEncontadoException;
import com.dayvid.aluraflix_api.model.DadosVideoDTO;
import com.dayvid.aluraflix_api.model.Video;
import com.dayvid.aluraflix_api.model.VideoDTO;
import com.dayvid.aluraflix_api.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository repository;

	@Transactional
	public VideoDTO salvarVideo(VideoDTO video) {
		repository.save(new Video(video));
		return video;
	}

	@Transactional(readOnly = true)
	public List<VideoDTO> listarTodosVideos() {
		return repository.findAll().stream().map(VideoDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public VideoDTO buscarVideo(Long id) {
		if (!repository.existsById(id)) {
			throw new ObjetoNaoEncontadoException("Video não encontrado");
		}
		return new VideoDTO(repository.getReferenceById(id));
	}

	@Transactional
	public VideoDTO atualizarVideo(DadosVideoDTO dadosVideo) {
		if (!repository.existsById(dadosVideo.id())) {
			throw new ObjetoNaoEncontadoException("Video não encontrado");
		}

		var video = repository.getReferenceById(dadosVideo.id());
		video.atualizarDados(dadosVideo);

		return new VideoDTO(video);
	}

	@Transactional
	public String deletarVideo(Long id) {
		repository.deleteById(id);
		return "Video deletado com sucesso.";
	}

}
