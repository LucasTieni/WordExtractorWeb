package com.lucastieni.WordExtractor.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucastieni.WordExtractor.exception.ErroAutenticacao;
import com.lucastieni.WordExtractor.exception.RegraNegocioException;
import com.lucastieni.WordExtractor.model.entity.Usuario;
import com.lucastieni.WordExtractor.model.repository.TUsuarioRepository;
import com.lucastieni.WordExtractor.service.TUsuarioService;

@Service
public class TUsuarioServiceImpl implements TUsuarioService {

	private TUsuarioRepository repository;
	
	@Autowired
	public TUsuarioServiceImpl(TUsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String password) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado");
		}
		
		if(!usuario.get().getPassword().equals(password)) {
			throw new ErroAutenticacao("Senha Inválida");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvaUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com esse email!");
		}
		
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return repository.findById(id);
	}

}
