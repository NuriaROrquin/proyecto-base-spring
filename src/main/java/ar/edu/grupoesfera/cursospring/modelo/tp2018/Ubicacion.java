package ar.edu.grupoesfera.cursospring.modelo.tp2018;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Ubicacion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Float latitud;
	
	private Float longitud;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getLatitud() {
		return latitud;
	}
	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}
	public Float getLongitud() {
		return longitud;
	}
	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

}
