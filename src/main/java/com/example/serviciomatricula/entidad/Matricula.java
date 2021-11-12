package com.example.serviciomatricula.entidad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="matriculas")
public class Matricula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMatricula;
	private Integer anio;
	private String cicloAcademico;
	
	@OneToOne(mappedBy = "matricula")
	private Estudiante estudiante;


	@OneToMany(mappedBy = "matricula")
	List<Curso> itemCurso =new ArrayList<>();


	public Integer getIdMatricula() {
		return idMatricula;
	}


	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}


	public Integer getAnio() {
		return anio;
	}


	public void setAnio(Integer anio) {
		this.anio = anio;
	}


	public String getCicloAcademico() {
		return cicloAcademico;
	}


	public void setCicloAcademico(String cicloAcademico) {
		this.cicloAcademico = cicloAcademico;
	}


	public Estudiante getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}


	public List<Curso> getItemCurso() {
		return itemCurso;
	}


	public void setItemCurso(List<Curso> itemCurso) {
		this.itemCurso = itemCurso;
	}
	
}
