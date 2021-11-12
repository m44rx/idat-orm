package com.example.serviciomatricula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.serviciomatricula.entidad.Curso;
import com.example.serviciomatricula.servicio.CursosService;

@RestController
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursosService servicio;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Curso>> listarCurso(){
		return new ResponseEntity<List<Curso>>(servicio.listarCurso(), HttpStatus.OK);
	}
	
	@PostMapping("/grabar")
	public ResponseEntity<?> grabarCurso(@RequestBody Curso curso){
		servicio.grabarCurso(curso);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	
	//obtener por ID
}