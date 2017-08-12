package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Service("inicializacion")
@Transactional
public class Inicializacion {

    @Inject
    private SessionFactory sessionFactory;

    @PostConstruct
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void cargarDatos(){
        final Session session = sessionFactory.openSession();
        Persona p1 = new Persona();
        p1.setApellido("Ismael");
        p1.setEmail("seba@seba.com");
        p1.setNombre("Sebastian");
        session.save(p1);
        Persona p2 = new Persona();
        p2.setApellido("Messi");
        p2.setEmail("seba@seba.com");
        p2.setNombre("Lionel");
        session.save(p2);
        Persona p3 = new Persona();
        p3.setApellido("higuain");
        p3.setEmail("seba@seba.com");
        p3.setNombre("Gonzalo");
        session.save(p3);
        Persona p4 = new Persona();
        p4.setApellido("Rojo");
        p4.setEmail("seba@seba.com");
        p4.setNombre("Marcos");
        session.save(p4);
    }
}
