package com.example.serviciomatricula.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.serviciomatricula.repository.ProfesorRepositorio;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private ProfesorRepositorio repositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Profesor usuario = repositorio.findByUsuario(username);
//		UserBuilder user = null;
//		if(usuario == null)
//			throw new UsernameNotFoundException("Usuario no existe");
//		else {
//			user = User.withUsername(username);
//			user.password(new BCryptPasswordEncoder().encode(usuario.getContrasenia()));
//			user.roles(usuario.getRol());
//		}
//		return user.build();
		if ("javainuse".equals(username)) {
			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else
			throw new UsernameNotFoundException("User not found with username: " + username);

	}

}
