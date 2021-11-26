package com.example.serviciomatricula.servicio;

import java.util.List;

import com.example.serviciomatricula.entidad.Estudiante;

public interface EstudianteService {

	List<Estudiante> listar();
	Estudiante ObtenerId(Integer id);
	void grabar(Estudiante curso);
	void eliminar(Integer id);
}
