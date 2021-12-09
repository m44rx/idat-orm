package com.example.serviciomatricula.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // No olvidar declarar esta clase con la anotacion '@Component'
    // Con esta clase trabajaremos las excepciones de la capa de consulta a bd
    // esta implementa de AuthenticationEntryPoint
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

              response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Inautorizado");  
    }
    
}
