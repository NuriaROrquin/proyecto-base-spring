package ar.edu.grupoesfera.cursospring.modelo;

import javax.persistence.*;

@Entity 
@Table(name = "FARMACIA")
public class Farmacia {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOMBRE", nullable=false)
	private String nombre;

	@Column(name = "TELEFONO", nullable=true)
	private String telefono;

	@Column(name = "DIA_DE_TURNO")
	private String diaDeTurno;

	@OneToOne(cascade=CascadeType.ALL)
	private Punto geoLocalizacion;

	@OneToOne(cascade=CascadeType.ALL)
	private Direccion direccion;

	public Farmacia(String nombre) {
		this.nombre = nombre;
	}

	public Farmacia(){}

	public Punto getGeoLocalizacion() {
		return geoLocalizacion;
	}
	public void setGeoLocalizacion(Punto geoLocalizacion) {
		this.geoLocalizacion = geoLocalizacion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getDiaDeTurno() {
		return diaDeTurno;
	}

	public void setDiaDeTurno(String diaDeTurno) {
		this.diaDeTurno = diaDeTurno;
	}
}
