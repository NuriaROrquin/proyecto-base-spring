package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.SpringTest;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import javax.inject.Inject;

public class UsuarioDaoTest extends SpringTest {

    @Inject
    private UsuarioDao dao;

    @Test
    @Transactional
    @Rollback
    public void guardar(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");

        // ejecucion
        dao.guardar(seba);

         // comprobacion
        assertThat(getSession().get(Usuario.class, seba.getId())).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void buscarPorMail(){
        // preparacion
        Usuario seba = new Usuario();
        seba.setEmail("seba@seba.com");
        seba.setPassword("1234");
        getSession().save(seba);

        // ejecucion
        Usuario buscado = dao.buscarPor(seba.getEmail());

        // comprobacion
        assertThat(buscado).isNotNull();
    }
}
