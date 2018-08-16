package ar.edu.grupoesfera.cursospring.modelo.tp2018;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.grupoesfera.cursospring.SpringTest;
import ar.edu.grupoesfera.cursospring.modelo.Farmacia;

import static org.assertj.core.api.Assertions.*;

public class Respuestas extends SpringTest{

	@Before
	public void setup() {
		
		Continente europa = new Continente();
		europa.setNombre("europa");
		getSession().save(europa);
		
		Continente america = new Continente();
		america.setNombre("america");
		getSession().save(america);
		
		Pais inglaterra = new Pais();
		inglaterra.setIdioma("ingles");
		inglaterra.setContinente(europa);
		getSession().save(inglaterra);
		
		Pais argentina = new Pais();
		argentina.setIdioma("español");
		argentina.setContinente(america);
		getSession().save(argentina);
	}
	
    @Test
    @Transactional
    @Rollback
    // Hacer con junit un test que busque todos los países de habla inglesa.
	public void ejercicio2() {
    	final List paises = getSession().createCriteria(Pais.class)
                .add(Restrictions.eq("idioma", "ingles"))
                .list();
        assertThat(paises).hasSize(1);
	}
    
    @Test
    @Transactional
    @Rollback
    // Hacer con junit un test que busque todos los países del continente europeo.
	public void ejercicio3() {
    	final List paises = getSession().createCriteria(Pais.class)
                .createAlias("continente", "cont")
                .add(Restrictions.eq("cont.nombre", "europa"))
                .list();
        assertThat(paises).hasSize(1);
	}
}
