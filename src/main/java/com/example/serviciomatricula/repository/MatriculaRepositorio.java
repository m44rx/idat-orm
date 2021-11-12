package com.example.serviciomatricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.serviciomatricula.entidad.Matricula;

public interface MatriculaRepositorio extends JpaRepository<Matricula, Integer>{

}
