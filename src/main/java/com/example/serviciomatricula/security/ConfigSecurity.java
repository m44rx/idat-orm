package com.example.serviciomatricula.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("wilder").password(password().encode("123")).roles("INSTRUCTOR");
		auth.inMemoryAuthentication().withUser("juan").password(password().encode("123")).roles("INST");
		auth.inMemoryAuthentication().withUser("jair").password(password().encode("123")).roles("USER");
			}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.antMatchers("/curso/listar").hasAnyRole("INSTRUCTOR","USER");
//			.antMatchers("/curso/listar").permitAll()
			.antMatchers("/curso/listar").access("hasRole('INSTRUCTOR')");
		http.authorizeRequests().and().httpBasic();
		http.authorizeRequests().and().csrf().disable();

	}

	@Bean
	public PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
}
