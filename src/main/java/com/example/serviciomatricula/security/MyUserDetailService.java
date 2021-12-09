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
	
	// *** 'sf' = springframework

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Se valida la implementacion en bd con el username, añadir antes la consulta en el repositorio para buscar por 'username'
		Profesor usuario = repositorio.findByUsuario(username);
		// Crear un 'UserBuilder' de springframework.security.core.userdetails.user.userbuilder
		UserBuilder user = null;
		if(usuario == null)
		//Si el username no existe se manda la exception
			throw new UsernameNotFoundException("Usuario no existe");
		else {
			// Como el username si existe en db
		// Se agrega el usuario - user 'UserBuilder' con 'User' de sf.security.core.userdetails.User
			user = User.withUsername(username);
		// Se agrega su contraseña al UserBuilder
			user.password(new BCryptPasswordEncoder().encode(usuario.getContrasenia()));
		// Se agrega sus roles
			user.roles(usuario.getRol());
		}
		// Por ultimo se construye con .build y retorna el user - UserBuilder
		return user.build();
	}

}
