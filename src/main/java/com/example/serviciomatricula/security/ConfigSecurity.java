package com.example.serviciomatricula.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

	// ****** Atencio, al usar token, debemos añadir la anotacion '@EnableGlobalMethodSecurity(prePostEnabled = true)' 
	// esto con el fin de poder usar los tokens

	// Clase de acceso a bd
	@Autowired
	private MyUserDetailService userDetailService;

	// Clase para manejo de errores
	// Inyectamos el entryPoint para usarlo en el configure(HttpSecurity)
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	// Clase de filto que valida el token y permite acceso
	// Inyectamos el filter para usarlo en el configure(HttpSecurity)
	@Autowired
	private JwtAuthTokenFilter filter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(password());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			
			// Estamos configurando de tal forma que todos puedan acceder al endpoint
			.antMatchers("/creartoken").permitAll()
			// Una vez las peticiones se han autenticado, pasan, sino usamos el exceptionHandling para menjar la excepcion
			.anyRequest().authenticated().and().exceptionHandling()
			// Usamos la clase de excepciones que creamos 'JwtAuthenticationEntryPoint' para manejar la excepcion o manejo de error
			.authenticationEntryPoint(entryPoint).and()
			// Si todo esta bien, procedemos almacenar en el manager, creamos una politica de tipo STATELESS ** investigar politicas
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().and().csrf().disable();
		// Agregamos el filter - JwtAuthTokenFilter y el 'UsernamePassswordAuthenticationFilter' - tipo filter**
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}

	// Agregar el 'AuthenticationManager' que es un metodo que sobre escribimos de 'WebSecurityConfigurerAdapter'
	// No olvidar agregar la anotacion '@Bean'
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	
}
