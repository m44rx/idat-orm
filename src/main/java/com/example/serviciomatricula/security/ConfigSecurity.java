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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
//	@Autowired
//	private MyUserDetailService userDetailService;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("wilder").password(password().encode("123")).roles("INSTRUCTOR");
//		auth.inMemoryAuthentication().withUser("juan").password(password().encode("123")).roles("INST");
//		auth.inMemoryAuthentication().withUser("jair").password(password().encode("123")).roles("USER");
		auth.userDetailsService(userDetailService).passwordEncoder(password());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/curso/listar").hasAnyRole("INSTRUCTOR","USER")
//			.antMatchers("/curso/listar").permitAll()		
//			.antMatchers("/curso/listar").access("hasRole('INSTRUCTOR')");			
//		http.authorizeRequests().and().httpBasic();
//		http.authorizeRequests().and().csrf().disable();
		
		// We don't need CSRF for this example
		http.csrf().disable()
			    // dont authenticate this particular request
			    .authorizeRequests().antMatchers("/authenticate").permitAll().
			    // all other requests need to be authenticated
			    anyRequest().authenticated().and().
			    // make sure we use stateless session; session won't be used to store user's state.
					exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
	                           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


	}

	@Bean
	public PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
