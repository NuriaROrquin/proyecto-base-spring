package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.servicios.PersonaService;
import ar.edu.grupoesfera.cursospring.servicios.ServicioLogin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ControladorLoginTest {

	private ControladorLogin controladorPersonas = new ControladorLogin();
	private Usuario usuarioMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioLogin personaServiceMock;
	
	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		personaServiceMock = mock(ServicioLogin.class);
		controladorPersonas.setServicioLogin(personaServiceMock);
	}

	@Test
	public void loginConUsuarioYPasswordInorrectosDeberiaLlevarALoginNuevamente(){
		// preparacion
		when(personaServiceMock.consultarUsuario(any(Usuario.class))).thenReturn(null);

		// ejecucion
		ModelAndView modelAndView = controladorPersonas.validarLogin(usuarioMock, requestMock);

		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("login");
		assertThat(modelAndView.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
		verify(sessionMock, times(0)).setAttribute("ROL", "ADMIN");
	}
	
	@Test
	public void loginConUsuarioYPasswordCorrectosDeberiaLLevarAHome(){
		// preparacion
		Usuario usuarioEncontradoMock = mock(Usuario.class);
		when(usuarioEncontradoMock.getRol()).thenReturn("ADMIN");

		when(requestMock.getSession()).thenReturn(sessionMock);
		when(personaServiceMock.consultarUsuario(any(Usuario.class))).thenReturn(usuarioEncontradoMock);
		
		// ejecucion
		ModelAndView modelAndView = controladorPersonas.validarLogin(usuarioMock, requestMock);
		
		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/home");
		verify(sessionMock, times(1)).setAttribute("ROL", usuarioEncontradoMock.getRol());
	}
	

}
