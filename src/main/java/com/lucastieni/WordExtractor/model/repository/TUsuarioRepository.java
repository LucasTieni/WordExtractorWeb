package com.lucastieni.WordExtractor.model.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucastieni.WordExtractor.model.entity.TUsuario;

public interface TUsuarioRepository extends JpaRepository<TUsuario, Long>{

	boolean existsByEmail(String email);
	
	Optional<TUsuario> findByEmail(String email);
}
