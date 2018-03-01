package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.Persona;

import java.util.List;

public interface PersonaService {

	void guardar(Persona persona);

	List<Persona> listarTodas();
	
	Persona buscarPorId(Long id);
	
}
