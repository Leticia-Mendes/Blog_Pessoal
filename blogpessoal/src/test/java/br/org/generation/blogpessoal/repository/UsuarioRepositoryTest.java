package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.save(new Usuario(0L, "Letícia Mendes", "leticia@email.com", "123456"));
		
		usuarioRepository.save(new Usuario(0L, "Maria Joaquina Silva", "mariajoaquina@email.com", "123456"));
		
		usuarioRepository.save(new Usuario(0L, "João Silva", "joaosilva@email.com", "123456"));
		
		usuarioRepository.save(new Usuario(0L, "Renata Silva", "renatasouza@email.com", "123456"));
		
	}
	
	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("mariajoaquina@email.com");
		assertTrue(usuario.get().getUsuario().equals("mariajoaquina@email.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuario() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Maria Joaquina Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("João Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Renata Silva"));
	}
	
}
