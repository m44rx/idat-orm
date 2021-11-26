package com.example.serviciomatricula.servicio;

import java.util.List;

import com.example.serviciomatricula.entidad.Profesor;

public interface ProfesorService {

	List<Profesor> listar();
	Profesor ObtenerId(Integer id);
	void grabar(Profesor profesor);
	void eliminar(Integer id);
}
