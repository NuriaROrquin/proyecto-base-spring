package ar.edu.grupoesfera.cursospring.modelo;

import ar.edu.grupoesfera.cursospring.SpringTest;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class FarmaciasTest extends SpringTest {

    @Before
    public void setup() {

        Barrio caballito = new Barrio("Caballito");
        getSession().save(caballito);

        Barrio flores = new Barrio("Flores");
        getSession().save(flores);

        Farmacia farmacia1 = new Farmacia("Farmacia Alfa", "Lunes");
        Direccion peruAl100 = new Direccion("Peru", "120", caballito);
        farmacia1.setDireccion(peruAl100);
        getSession().save(farmacia1);

        Farmacia farmacia2 = new Farmacia("Farmacia Beta", "Martes");
        Direccion peruAl300 = new Direccion("Peru", "333", caballito);
        farmacia2.setDireccion(peruAl300);
        getSession().save(farmacia2);

        Farmacia farmacia3 = new Farmacia("Farmacia Omega", "Miercoles");
        Direccion limaAl400 = new Direccion("Lima", "402", caballito);
        farmacia3.setDireccion(limaAl400);
        getSession().save(farmacia3);

        Farmacia farmacia4 = new Farmacia("Farmacia Gamma", "Jueves");
        Direccion piedrasAl100 = new Direccion("Piedras", "111", flores);
        farmacia4.setDireccion(piedrasAl100);
        getSession().save(farmacia4);
    }

    @Test
    @Transactional
    @Rollback
    public void buscarLasFarmaciasDeTurnoLosMartesDeberiaDevolverUnaFarmacia() {
        final List farmacias = getSession().createCriteria(Farmacia.class)
                .add(Restrictions.eq("diaDeTurno", "Martes"))
                .list();
        assertThat(farmacias).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback
    public void buscarLasFarmaciasDeUnaCalleDeberiaDevolverDosFarmacias() {
        final List farmacias = getSession().createCriteria(Farmacia.class)
                .createAlias("direccion", "direccionBuscada")
                .add(Restrictions.eq("direccionBuscada.calle", "Peru"))
                .list();
        assertThat(farmacias).hasSize(2);
    }


    @Test
    @Transactional
    @Rollback
    public void buscarLasFarmaciasDeUnBarrioDeberiaDevolverTresFarmacias() {
        final List farmacias = getSession().createCriteria(Farmacia.class)
                .createAlias("direccion.barrio", "barrioBuscado")
                .add(Restrictions.eq("barrioBuscado.nombre", "Caballito"))
                .list();
        assertThat(farmacias).hasSize(3);
    }

}
