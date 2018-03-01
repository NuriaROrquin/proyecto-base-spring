package ar.edu.grupoesfera.cursospring.servicios;

import static org.mockito.Mockito.*;

import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.UsuarioExistente;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

public class ServicioLoginTest {

    private ServicioLoginImpl servicioLogin;
    private Usuario usuarioMock;
    private UsuarioDao usuarioDaoMock;
    private ServicioMail servicioMailMock;

    @Before
    public void init(){
        servicioLogin = new ServicioLoginImpl();
        usuarioMock = mock(Usuario.class);

        usuarioDaoMock = mock(UsuarioDao.class);
        servicioLogin.setUsuarioDao(usuarioDaoMock);

        servicioMailMock = mock(ServicioMail.class);
        servicioLogin.setServicioMail(servicioMailMock);
    }

    @Test(expected = UsuarioExistente.class)
    public void registrarmeDeberiaLanzarUsuarioExisteSiExisteUnUsuarioConMismoMail() throws UsuarioExistente {

        // preparacion
        when(usuarioDaoMock.buscarPor(usuarioMock.getEmail())).thenReturn(new Usuario());

        // ejecucion
        servicioLogin.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, never()).enviarMailDeBienvenida(usuarioMock);
    }

    @Test(expected = Exception.class)
    public void registrarmeDeberiaLanzarExceptionSiOcurreUnErrorAlGuardarElUsuarioNuevo() throws UsuarioExistente {

        // preparacion
        when(usuarioDaoMock.buscarPor(usuarioMock.getEmail())).thenReturn(null);
        doThrow(HibernateException.class).when(usuarioDaoMock).guardar(usuarioMock);

        // ejecucion
        servicioLogin.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, never()).enviarMailDeBienvenida(usuarioMock);
    }

    @Test
    public void registrarmeDeberiaGuardarElNuevoUsuarioSiElUsuarioNoExiste() throws UsuarioExistente {

        // preparacion
        when(usuarioDaoMock.buscarPor(usuarioMock.getEmail())).thenReturn(null);

        // ejecucion
        servicioLogin.registrar(usuarioMock);

        // validacion
        verify(servicioMailMock, times(1)).enviarMailDeBienvenida(usuarioMock);
    }
}