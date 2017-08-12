package ar.edu.grupoesfera.cursospring.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name="COMUNA")
public class Comuna {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOMBRE", nullable=false)
	private String nombre;
	
    @OneToMany(mappedBy = "comuna", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = CascadeType.ALL)
    @OrderBy(clause = "nombre")
    private List<Barrio> barrios = new LinkedList<Barrio>();

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

	public List<Barrio> getBarrios() {
		return barrios;
	}

	public void setBarrios(List<Barrio> barrios) {
		this.barrios = barrios;
	}
	

	public void addBarrio(Barrio barrio){
		this.barrios.add(barrio);
		barrio.setComuna(this);
	}
}
