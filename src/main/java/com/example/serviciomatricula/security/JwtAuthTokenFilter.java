package com.example.serviciomatricula.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter{
    
    //Esta clase que extiende de OncePerRequestFilter para intersecar las peticiones web
    // No olvidar declarar la clase como '@Component'


    // *** Esta clase filter es la primera linea, aqui llega el token y debemos desencriptar
    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // Esta clase recibe todo el cuerpo cabecera, payload y sign

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                // Cabecera del cuerpo del token
                final String requesToken= request.getHeader("Authorization");
                
                String username = null;
                String jwtToken = null;

                if(requesToken != null && requesToken.startsWith("Bearer ")){
                    // Rescatamos de todo el token enviado y excluimos la palabra por default que suele usarse 'Bearer ', que son 7 caracteres
                    // y asignamos el token a nuestra variable jwtToken
                    jwtToken = requesToken.substring(7);
                    // Encapculamos dentro del try-catch, para controlar los errores
                    try {
                        // Hacemos usto del utilitario JwtTokenUtil, buscamos/obtenemos y asignamos el username a nuestra variable
                        username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                    } catch (IllegalArgumentException e) {
                        // Algunos argumentos que conforman el token son incorrecptos y son ilegales, puede ser que tambien el token haya sido corrompido
                        // Si hubiese problemas debemos manejar con IllegalArgumentException, las excepciones
                        System.out.println("Usuario ilegal");
                    }catch(ExpiredJwtException e){
                        // El usuario es correcto pero el tiempo del token ha expirado
                        System.out.println("El token ha expirado");
                    }
                }else{
                    // Podemos enviar syso, pero es mejor manejar 'log' - buenas practicas
                    logger.warn("Error en el token enviado");
                }

                // *** Tener en cuenta que la logica dentro del condicional casi simpre se repite excepto se requiera personalizar por tema del negocio
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    // Con el if validamos que en el SecurityContexHolder la autentificacion sea la que vamos a crear, sea una sola, sea nueva
                    // UserDetails userDetails = userDetailService.loadUserByUsername(username); *** Podemos usar esta forma - no confirmada
                    UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
                    if(jwtTokenUtil.validateToken(jwtToken, userDetails)){
                        // Validamos el token con el utilitario JwtTokenUtil
                        // Creamos un nuevo UsernamePasswordAuthenticationToken y le pasamos el nuevo userDetails creado, credenciales null, y la coleccion de autorizaciones
                        UsernamePasswordAuthenticationToken usernameAuthToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                        // En esta linea cargamos la construccion con el request la creacion y autenticacion como recurso para la web
                        usernameAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // Por ultimo devolvemos el contexto con el usernameAuthToken configurado
                        SecurityContextHolder.getContext().setAuthentication(usernameAuthToken);
                    }
                }

            // **** No olvidar que los filtros deben pasar por la cadena de request y response - filterchain**
           filterChain.doFilter(request, response);     
    }
    
}
