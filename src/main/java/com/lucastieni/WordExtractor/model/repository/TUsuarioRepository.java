package com.lucastieni.WordExtractor.model.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucastieni.WordExtractor.model.entity.Usuario;

public interface TUsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);
}
