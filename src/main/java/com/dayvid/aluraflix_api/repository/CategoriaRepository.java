package com.dayvid.aluraflix_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dayvid.aluraflix_api.model.Categoria;

import jakarta.validation.constraints.NotBlank;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	boolean existsByTituloIgnoreCase(String titulo);

}
