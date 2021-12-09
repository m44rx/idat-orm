package com.example.serviciomatricula.controller;

import com.example.serviciomatricula.entidad.JwtRequest;
import com.example.serviciomatricula.entidad.JwtResponse;
import com.example.serviciomatricula.security.JwtTokenUtil;
import com.example.serviciomatricula.security.MyUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AutenticadorJWTController {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping("/creartoken")
    public ResponseEntity<?> crearToken(@RequestBody JwtRequest request) throws Exception {
        //intentando validar token - falta un paso mas
        // 1*** Primera se valida que user y pass sea correcto, sino lo es salta las 'Exception', si lo es queda
        authenticate2(request.getUsername(), request.getPassword());
        // la capa que interactua con la bd es UserDetails de springframework.security.core.UserDetails
        // recordar inyectar la dependencia personalizada de UserDetails - para este caso 'MyUserDetailService'
        // 2*** Segundo se valida en bd con el metodo personalizado userDetailsService
        final UserDetails userDetail = myUserDetailService.loadUserByUsername(request.getUsername());
        // Hacemos uso del utilitario JwtTokenUtil donde estan las funciones predefinidas y generamos el token
        final String token = jwtUtil.generateToken(userDetail); 
        // Recordar que por buenas practicas el token - String, no puede viajar solo, este debe ir en un objeto
        // return ResponseEntity.ok(token);
        return ResponseEntity.ok(new JwtResponse(token));
    }
//interactuando con el Auth Manager
    private void authenticate2(String username, String password) throws Exception {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USUARIO DEHSABILITADO", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credencial incorrecta", e);
        }
    }

}
