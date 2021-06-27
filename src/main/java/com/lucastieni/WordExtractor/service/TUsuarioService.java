package com.lucastieni.WordExtractor.service;

import java.util.Optional;

import com.lucastieni.WordExtractor.model.entity.Usuario;

public interface TUsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvaUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	Optional<Usuario> obterPorId(Long id);
}
