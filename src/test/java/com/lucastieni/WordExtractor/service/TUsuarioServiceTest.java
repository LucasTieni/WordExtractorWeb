package com.lucastieni.WordExtractor.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lucastieni.WordExtractor.exception.ErroAutenticacao;
import com.lucastieni.WordExtractor.exception.RegraNegocioException;
import com.lucastieni.WordExtractor.model.entity.TUsuario;
import com.lucastieni.WordExtractor.model.repository.TUsuarioRepository;
import com.lucastieni.WordExtractor.service.impl.TUsuarioServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class TUsuarioServiceTest {
	
	@SpyBean
	TUsuarioServiceImpl service;
	
	@MockBean
	TUsuarioRepository repository;
	
	@Test(expected = Test.None.class)
	public void deveSalvarUmUsuario() {
		//cenario
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		TUsuario usuario = TUsuario.builder()
				.id(1l)
				.Name("nome")
				.email("usuario@gmail.com")
				.password("123").build();
		
		Mockito.when(repository.save(Mockito.any(TUsuario.class))).thenReturn(usuario);
		
		//acao
		TUsuario usuarioSalvo = service.salvaUsuario(new TUsuario());
		
		//verificacao
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		Assertions.assertThat(usuarioSalvo.getName()).isEqualTo("nome");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("usuario@gmail.com");
		Assertions.assertThat(usuarioSalvo.getPassword()).isEqualTo("123");
	}

	@Test(expected =RegraNegocioException.class)
	public void naoDeveSalvarUmUsuarioJaCadastrado() {
		//cenario 
		String email = "usuario@gmail.com";
		TUsuario usuario = TUsuario.builder().email(email).build();
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);	
		
		//acao
		service.salvaUsuario(usuario);
		
		//verificacao
		Mockito.verify(repository, Mockito.never()).save(usuario);
	}
	
	@Test
	public void deveAutentitcasUmUsuarioComSucesso() {
		//cenário
		String email = "usuario@gmail.com";
		String password = "senha";
		
		TUsuario usuario = TUsuario.builder().email(email).password(password).id(1l).build();
		Mockito.when( repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//acao
		TUsuario result = service.autenticar(email, password);
		
		//verificao
		Assertions.assertThat(result).isNotNull();
		
	}
	
	@Test
	public void deveLancarErroQuandoSenhaNaoBater(){
		//cenario
		String senha = "123";
		TUsuario usuario = TUsuario.builder().email("usuario@gmail.com").password(senha).build();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		//acao
		Throwable exception = Assertions.catchThrowable(() -> service.autenticar("usuario@gmail.com", "senha"));
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha Inválida");
		
	}
	
	
	@Test
	public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComOEmailInformado() {
		//cenario
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		//acao
		Throwable exception = Assertions.catchThrowable( () -> service.autenticar("usuario@gmail.com", "123") );
		
		//verificacao
		Assertions.assertThat(exception)
			.isInstanceOf(ErroAutenticacao.class)
			.hasMessage("Usuário não encontrado");
	}
	
	
	@Test(expected = Test.None.class)
	public void deveValidarEmail() {
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		//acao
		service.validarEmail("usuario@gmail.com");
	}
	
	@Test (expected = RegraNegocioException.class)
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		//acao
		service.validarEmail("usuario@gmail.com");
	}
	
}
