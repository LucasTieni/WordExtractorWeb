package com.lucastieni.WordExtractor.service;

import com.lucastieni.WordExtractor.model.entity.TUsuario;

public interface TUsuarioService {
	
	TUsuario autenticar(String email, String senha);
	
	TUsuario salvaUsuario(TUsuario usuario);
	
	void validarEmail(String email);
	
}
