package com.dayvid.aluraflix_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dayvid.aluraflix_api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	boolean findByTituloIgnoreCase(String titulo);

}
