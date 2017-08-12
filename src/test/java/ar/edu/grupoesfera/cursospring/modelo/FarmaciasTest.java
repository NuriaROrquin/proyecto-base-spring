package ar.edu.grupoesfera.cursospring.modelo;

import ar.edu.grupoesfera.cursospring.SpringTest;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class FarmaciasTest extends SpringTest{

    @Before
    public void setup(){

        Barrio caballito = new Barrio("Caballito");
        getSession().save(caballito);

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
    }

    @Test @Transactional @Rollback
    public void buscarLasFarmaciasDeTurnoLosMartesDeberiaDevolverUnaFarmacia(){
        final List farmacias = getSession().createCriteria(Farmacia.class)
                .add(Restrictions.eq("diaDeTurno", "Martes"))
                .list();
        assertThat(farmacias).hasSize(1);
    }

    @Test @Transactional @Rollback
    public void buscarLasFarmaciasDeUnaCalleDeberiaDevolverDosFarmacias(){
        final List farmacias = getSession().createCriteria(Farmacia.class)
                .createAlias("direccion", "direccionBuscada")
                .add(Restrictions.eq("direccionBuscada.calle", "Peru"))
                .list();
        assertThat(farmacias).hasSize(2);
    }
}
