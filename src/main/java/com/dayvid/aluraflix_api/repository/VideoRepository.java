package com.dayvid.aluraflix_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dayvid.aluraflix_api.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

	Video findByTitulo(String titulo);
	
}
