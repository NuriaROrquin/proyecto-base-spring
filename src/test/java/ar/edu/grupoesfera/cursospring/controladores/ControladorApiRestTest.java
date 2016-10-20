package ar.edu.grupoesfera.cursospring.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.grupoesfera.cursospring.modelo.Persona;
import ar.edu.grupoesfera.cursospring.servicios.PersonaService;

public class ControladorApiRestTest {

	private PersonaService personaServiceMock;
	private ControladorApiRest controlador = new ControladorApiRest();
	
	@Before
	public void before(){
		personaServiceMock = mock(PersonaService.class);
		controlador.setPersonaService(personaServiceMock);
	}
	
	@Test
	public void testListarPersonas(){
		Persona personaMock = mock(Persona.class);
		List<Persona> lista = Arrays.asList(personaMock);
		when(personaServiceMock.listarTodas()).thenReturn(lista);
		
		List<Persona> personas = controlador.listarPersonas();
		
		verify(personaServiceMock, times(1)).listarTodas();
		assertThat(personas).hasSize(1);
		assertThat(personas).containsOnly(personaMock);
		
	}
}
