package com.example.serviciomatricula.servicio;

import java.util.List;

import com.example.serviciomatricula.entidad.Curso;

public interface CursosService {
	
	List<Curso> listarCurso();
	Curso obtenerCursoId(Integer id);
	void grabarCurso(Curso curso);
	void eliminarCurso(Integer id);

}
