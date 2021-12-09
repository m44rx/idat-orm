package com.example.serviciomatricula.entidad;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCurso;
	private String nombre;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_matricula")
	private Matricula matricula;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name = "cursosProfesor",
			joinColumns = @JoinColumn(name="id_curso"),
			inverseJoinColumns = @JoinColumn(name="id_profesor")
	)
	private Set<Profesor>itemProfesor = new HashSet<>();

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Set<Profesor> getItemProfesor() {
		return itemProfesor;
	}

	public void setItemProfesor(Set<Profesor> itemProfesor) {
		this.itemProfesor = itemProfesor;
	}

	
}
