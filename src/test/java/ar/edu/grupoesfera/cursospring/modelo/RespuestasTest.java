package ar.edu.grupoesfera.cursospring.modelo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.grupoesfera.cursospring.SpringTest;
import ar.edu.grupoesfera.cursospring.modelo.tp2018.Ciudad;
import ar.edu.grupoesfera.cursospring.modelo.tp2018.Continente;
import ar.edu.grupoesfera.cursospring.modelo.tp2018.Pais;
import ar.edu.grupoesfera.cursospring.modelo.tp2018.Ubicacion;

@SuppressWarnings("unchecked")
public class RespuestasTest extends SpringTest{

	@Before
	public void setup() {
		
		Continente europa = new Continente();
		europa.setNombre("europa");
		getSession().save(europa);
		
		Continente america = new Continente();
		america.setNombre("america");
		getSession().save(america);
		
		Pais inglaterra = new Pais();
		getSession().save(inglaterra);
		
		Ubicacion uLondres = new Ubicacion();
		uLondres.setLatitud(51.5072F);
		uLondres.setLongitud(-0.1275F);
		
		Ciudad londres = new Ciudad();
		londres.setUbicacion(uLondres);
		londres.setPais(inglaterra);
		getSession().save(londres);
		
		inglaterra.setIdioma("ingles");
		inglaterra.setCapital(londres);
		inglaterra.setContinente(europa);
		getSession().update(inglaterra);
		
		Pais argentina = new Pais();
		getSession().save(argentina);

		Ubicacion uBaires = new Ubicacion();
		uBaires.setLatitud(-34.6083F);
		uBaires.setLongitud(-58.3712F);
		
		Ciudad baires = new Ciudad();
		baires.setUbicacion(uBaires);
		baires.setPais(argentina);
		getSession().save(baires);
	
		argentina.setIdioma("español");
		argentina.setContinente(america);
		getSession().update(argentina);
	}
	
    @Test
    @Transactional
    @Rollback
    // Hacer con junit un test que busque todos los países de habla inglesa.
	public void ejercicio2() {
    	final List<Pais> paises = getSession().createCriteria(Pais.class)
                .add(Restrictions.eq("idioma", "ingles"))
                .list();
        assertThat(paises).hasSize(1);
	}
    
	@Test
    @Transactional
    @Rollback
    // Hacer con junit un test que busque todos los países del continente europeo.
	public void ejercicio3() {
    	final List<Pais> paises = getSession().createCriteria(Pais.class)
                .createAlias("continente", "cont")
                .add(Restrictions.eq("cont.nombre", "europa"))
                .list();
        assertThat(paises).hasSize(1);
	}
    
    @Test
    @Transactional
    @Rollback
    // Hacer con junit un test que busque todos los países cuya capital están al norte del trópico de cáncer.
	public void ejercicio4() {
    	final List<Pais> paises = getSession().createCriteria(Pais.class)
                .createAlias("capital", "capi")
                .createAlias("capi.ubicacion", "ubi")
                .add(Restrictions.gt("ubi.latitud", 23.43722F))
                .list();
        assertThat(paises).hasSize(1);
	}
    
    @Test
    @Transactional
    @Rollback
    // Hacer con junit un test que busque todas las ciudades del hemisferio sur 
	public void ejercicio5() {
    	final List<Ciudad> ciudades = getSession().createCriteria(Ciudad.class)
                .createAlias("ubicacion", "ubi")
                .add(Restrictions.gt("ubi.latitud", 0F))
                .list();
        assertThat(ciudades).hasSize(1);
	}
}
