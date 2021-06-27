package com.lucastieni.WordExtractor.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lucastieni.WordExtractor.model.entity.Usuario;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TUsuarioRepositoryTest {
	
	@Autowired
	TUsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = Usuario.builder().Name("usuario").email("usuario@gmail.com").build();
		entityManager.persist(usuario);
		
		//ação/ execução
		boolean result = repository.existsByEmail("usuario@gmail.com");
		
		//verificação
		Assertions.assertThat(result).isTrue();
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//cenario
		
		//ação
		boolean result = repository.existsByEmail("usuario@gmail.com");
		
		//verificacao
		Assertions.assertThat(result).isFalse();
		
	}
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		//cenario
		Usuario usuario = criarUsuario();
		
		//acao
		Usuario usuarioSalvo = repository.save(usuario);
		
		//verificacao
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}

	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		//cenario
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		//verificao
		Optional<Usuario> result = repository.findByEmail("usuario@gmail.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
	}
	
	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
		//cenario
		
		//verificao
		Optional<Usuario> result = repository.findByEmail("usuario@gmail.com");
		
		Assertions.assertThat(result.isPresent()).isFalse();
	}
	
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
						.Name("usuario")
						.email("usuario@gmail.com")
						.password("123")
						.build();
	}
}
