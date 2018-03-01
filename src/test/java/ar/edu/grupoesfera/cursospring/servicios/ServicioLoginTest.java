package ar.edu.grupoesfera.cursospring.servicios;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.UsuarioExistente;
import org.junit.Before;
import org.junit.Test;

public class ServicioLoginTest {

    private ServicioLoginImpl servicioLogin;
    private Usuario usuarioMock;
    private UsuarioDao usuarioDaoMock;

    @Before
    public void init(){
        servicioLogin = new ServicioLoginImpl();
        usuarioMock = mock(Usuario.class);
        usuarioDaoMock = mock(UsuarioDao.class);
        servicioLogin.setUsuarioDao(usuarioDaoMock);
    }

    @Test(expected = UsuarioExistente.class)
    public void registrarmeDeberiaLanzarUsuarioExisteSiExisteUnUsuarioConMismoMail() throws UsuarioExistente {

        // preparacion
        when(usuarioDaoMock.buscarPor(usuarioMock.getEmail())).thenReturn(new Usuario());

        // ejecucion
        servicioLogin.registrar(usuarioMock);
    }
}
