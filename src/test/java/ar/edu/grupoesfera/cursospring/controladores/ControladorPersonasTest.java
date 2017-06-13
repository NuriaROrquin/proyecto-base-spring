package ar.edu.grupoesfera.cursospring.controladores;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.servicios.PersonaService;

public class ControladorPersonasTest {

	private ControladorPersonas controladorPersonas = new ControladorPersonas();
	private Usuario usuarioMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private PersonaService personaServiceMock;
	
	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		personaServiceMock = mock(PersonaService.class);
		controladorPersonas.setPersonaService(personaServiceMock);
	}
	
	@Test
	public void loginConUsuarioYPasswordCorrectosDeberiaLLevarAHome(){
		// preparacion
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(usuarioMock.getRol()).thenReturn("ADMIN");
		when(personaServiceMock.validarUsuario(anyString(), anyString())).thenReturn(usuarioMock);
		
		// ejecucion
		ModelAndView modelAndView = controladorPersonas.login(usuarioMock, requestMock);
		
		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("home");
		verify(sessionMock, times(1)).setAttribute("ROL", "ADMIN");
	}
	
	@Test
	public void loginConUsuarioYPasswordInorrectosDeberiaLlevarALoginNuevamente(){
		// preparacion
		when(personaServiceMock.validarUsuario(anyString(), anyString())).thenReturn(null);
		
		// ejecucion
		ModelAndView modelAndView = controladorPersonas.login(usuarioMock, requestMock);
		
		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("login");
		assertThat(modelAndView.getModel().get("error")).isEqualTo("usuaio-invalido");
		verify(sessionMock, times(0)).setAttribute("ROL", "ADMIN");
	}
}
