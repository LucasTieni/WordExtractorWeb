package com.lucastieni.WordExtractor.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucastieni.WordExtractor.api.dto.TUsuarioDTO;
import com.lucastieni.WordExtractor.exception.ErroAutenticacao;
import com.lucastieni.WordExtractor.exception.RegraNegocioException;
import com.lucastieni.WordExtractor.model.entity.TUsuario;
import com.lucastieni.WordExtractor.service.TUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class TUsuarioResource {
	
	private TUsuarioService service;
	
	public TUsuarioResource ( TUsuarioService service) {
		this.service = service;
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar ( @RequestBody TUsuarioDTO dto) {
		try {
			TUsuario usuarioAutenticado = service.autenticar( dto.getEmail(), dto.getPassword());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity salvar( @RequestBody TUsuarioDTO dto ) {
		
		TUsuario usuario = TUsuario.builder()
				.Name(dto.getName())
				.email(dto.getEmail())
				.password(dto.getPassword()).build();
		
		try {
			TUsuario usuarioSalvo = service.salvaUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
}
