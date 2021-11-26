package com.example.serviciomatricula.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.serviciomatricula.entidad.Profesor;
import com.example.serviciomatricula.repository.ProfesorRepositorio;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private ProfesorRepositorio repositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Profesor usuario = repositorio.findByUsuario(username);
		UserBuilder user = null;
		if(usuario == null)
			throw new UsernameNotFoundException("Usuario no existe");
		else {
			user = User.withUsername(username);
			user.password(new BCryptPasswordEncoder().encode(usuario.getContrasenia()));
			user.roles(usuario.getRol());
		}
		return user.build();
	}

}
