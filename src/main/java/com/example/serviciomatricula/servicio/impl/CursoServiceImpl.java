package com.example.serviciomatricula.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.serviciomatricula.entidad.Curso;
import com.example.serviciomatricula.repository.CursoRepositorio;
import com.example.serviciomatricula.servicio.CursosService;

@Service
public class CursoServiceImpl implements CursosService {

	@Autowired
	private CursoRepositorio repositorio;
	
	@Override
	public List<Curso> listarCurso() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Curso obtenerCursoId(Integer id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public void grabarCurso(Curso curso) {
		// TODO Auto-generated method stub
		repositorio.save(curso);
	}

	@Override
	public void eliminarCurso(Integer id) {
		repositorio.deleteById(id);
	}

}
