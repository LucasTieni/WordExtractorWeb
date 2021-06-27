package com.lucastieni.WordExtractor.api.resource;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucastieni.WordExtractor.api.dto.TUsuarioDTO;
import com.lucastieni.WordExtractor.exception.ErroAutenticacao;
import com.lucastieni.WordExtractor.exception.RegraNegocioException;
import com.lucastieni.WordExtractor.model.entity.Usuario;
import com.lucastieni.WordExtractor.service.LancamentoService;
import com.lucastieni.WordExtractor.service.TUsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class TUsuarioResource {
	
	private final TUsuarioService service;
	private final LancamentoService lancamentoService;
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar ( @RequestBody TUsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = service.autenticar( dto.getEmail(), dto.getPassword());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity salvar( @RequestBody TUsuarioDTO dto ) {
		
		Usuario usuario = Usuario.builder()
				.Name(dto.getName())
				.email(dto.getEmail())
				.password(dto.getPassword()).build();
		
		try {
			Usuario usuarioSalvo = service.salvaUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("{id}/saldo")
	public ResponseEntity obterSaldo( @PathVariable("id") Long id) {
		Optional<Usuario> usuario = service.obterPorId(id);
		
		if (!usuario.isPresent()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
		return ResponseEntity.ok(saldo);
		
	}
}
