package com.lucastieni.WordExtractor.api.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucastieni.WordExtractor.api.dto.UsuarioDTO;
import com.lucastieni.WordExtractor.exception.ErroAutenticacao;
import com.lucastieni.WordExtractor.exception.RegraNegocioException;
import com.lucastieni.WordExtractor.model.entity.Usuario;
import com.lucastieni.WordExtractor.service.LancamentoService;
import com.lucastieni.WordExtractor.service.TUsuarioService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = TUsuarioResource.class)
@AutoConfigureMockMvc
public class UsuarioResourceTest {
	static final String API = "/api/usuarios";
	static final MediaType JSON = MediaType.APPLICATION_JSON;
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	TUsuarioService service;
	
	@MockBean
	LancamentoService lancamentoService;
	
	@Test
	public void deveAutenticarUmUsuario() throws Exception{
		//cenario
		String email = "usuario@email.com";
		String password = "123";
		
		UsuarioDTO dto = UsuarioDTO.builder().email(email).password(password).build();
		Usuario usuario = Usuario.builder().id(1l).email(email).password(password).build();
		
		Mockito.when(service.autenticar(email, password)).thenReturn(usuario);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//execucao e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post(API.concat("/autenticar"))
													.accept( JSON )
													.contentType( JSON )
													.content(json);
		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isOk())
			.andExpect( MockMvcResultMatchers.jsonPath("id").value(usuario.getId()))
			.andExpect( MockMvcResultMatchers.jsonPath("name").value(usuario.getName()))
			.andExpect( MockMvcResultMatchers.jsonPath("email").value(usuario.getEmail()));
	}
	
	@Test
	public void deveRetornarBadRequestAoObterErroDeAutenticacao() throws Exception{
		//cenario
		String email = "usuario@email.com";
		String password = "123";
		
		UsuarioDTO dto = UsuarioDTO.builder().email(email).password(password).build();
		
		Mockito.when(service.autenticar(email, password)).thenThrow(ErroAutenticacao.class);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//execucao e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post(API.concat("/autenticar"))
													.accept( JSON )
													.contentType( JSON )
													.content(json);
		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void deveCriarUmNovoUsuario() throws Exception{
		//cenario
		String email = "usuario@email.com";
		String password = "123";
		
		UsuarioDTO dto = UsuarioDTO.builder().email(email).password(password).build();
		Usuario usuario = Usuario.builder().id(1l).email(email).password(password).build();
		
		Mockito.when(service.salvaUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//execucao e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post(API)
													.accept( JSON )
													.contentType( JSON )
													.content(json);
		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isCreated())
			.andExpect( MockMvcResultMatchers.jsonPath("id").value(usuario.getId()))
			.andExpect( MockMvcResultMatchers.jsonPath("name").value(usuario.getName()))
			.andExpect( MockMvcResultMatchers.jsonPath("email").value(usuario.getEmail()));
	}
	
	@Test
	public void deveRetornarBadRequestAoTentarCriarUmNovoUsuarioInvalido() throws Exception{
		//cenario
		String email = "usuario@email.com";
		String password = "123";
		
		UsuarioDTO dto = UsuarioDTO.builder().email(email).password(password).build();
		
		Mockito.when(service.salvaUsuario(Mockito.any(Usuario.class))).thenThrow(RegraNegocioException.class);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//execucao e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post(API)
													.accept( JSON )
													.contentType( JSON )
													.content(json);
		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isBadRequest());
	}
}
