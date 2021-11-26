package com.example.serviciomatricula.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.serviciomatricula.entidad.Estudiante;
import com.example.serviciomatricula.repository.EstudianteRepositorio;
import com.example.serviciomatricula.servicio.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService {

	@Autowired
	private EstudianteRepositorio repositorio;

	@Override
	public List<Estudiante> listar() {
		return repositorio.findAll();
	}

	@Override
	public Estudiante ObtenerId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public void grabar(Estudiante estudiante) {
		repositorio.save(estudiante);
	}

	@Override
	public void eliminar(Integer id) {
		repositorio.deleteById(id);
	}
}
