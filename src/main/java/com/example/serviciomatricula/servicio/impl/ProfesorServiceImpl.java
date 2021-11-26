package com.example.serviciomatricula.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.serviciomatricula.entidad.Profesor;
import com.example.serviciomatricula.repository.ProfesorRepositorio;
import com.example.serviciomatricula.servicio.ProfesorService;

public class ProfesorServiceImpl implements ProfesorService {

	@Autowired
	private ProfesorRepositorio repositorio;
	
	@Override
	public List<Profesor> listar() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Profesor ObtenerId(Integer id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public void grabar(Profesor profesor) {
		// TODO Auto-generated method stub
		repositorio.save(profesor);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repositorio.deleteById(id);
	}

}
