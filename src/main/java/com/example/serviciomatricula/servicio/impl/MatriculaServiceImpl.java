package com.example.serviciomatricula.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.serviciomatricula.entidad.Matricula;
import com.example.serviciomatricula.repository.MatriculaRepositorio;
import com.example.serviciomatricula.servicio.MatriculaService;

public class MatriculaServiceImpl implements MatriculaService {

	@Autowired
	private MatriculaRepositorio repositorio;
	
	@Override
	public List<Matricula> listar() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Matricula ObtenerId(Integer id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public void grabar(Matricula matricula) {
		// TODO Auto-generated method stub
		repositorio.save(matricula);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repositorio.deleteById(id);
	}

}
