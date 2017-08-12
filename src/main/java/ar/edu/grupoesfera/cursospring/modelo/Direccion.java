package ar.edu.grupoesfera.cursospring.modelo;

import javax.persistence.*;

@Entity 
@Table(name = "DIRECCION")
public class Direccion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CALLE", nullable=false)
	private String calle;
	
	@Column(name = "NUMERO", nullable=true)
	private String numero;
	
	@ManyToOne
    @JoinColumn(name = "ID_BARRIO", nullable = false)
	private Barrio barrio;

	public Direccion(String calle, String numero, Barrio barrio) {
		this.calle = calle;
		this.numero = numero;
		this.barrio = barrio;
	}

	public Direccion(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}
	
	
}
