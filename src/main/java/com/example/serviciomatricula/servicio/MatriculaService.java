package com.example.serviciomatricula.servicio;

import java.util.List;

import com.example.serviciomatricula.entidad.Matricula;

public interface MatriculaService {

	List<Matricula> listar();
	Matricula ObtenerId(Integer id);
	void grabar(Matricula matricula);
	void eliminar(Integer id);
}
