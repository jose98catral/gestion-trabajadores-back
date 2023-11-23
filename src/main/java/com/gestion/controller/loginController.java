/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.controller;

import com.gestion.config.JwtTokenProvider;
import com.gestion.login.LoginRequest;
import com.gestion.login.LoginResponse;
import com.gestion.model.Usuario;
import com.gestion.service.UsuarioService;
import static org.hibernate.internal.CoreLogging.logger;
import static org.hibernate.internal.HEMLogging.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class loginController {

 

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    Logger log = LoggerFactory.getLogger(this.getClass());
    
    
   @PostMapping("login")
   public LoginResponse login(@RequestBody LoginRequest login){
       
       try{
           
       
Authentication auth = new UsernamePasswordAuthenticationToken(login.username(),login.password());

log.info("Usuario: " +auth);
    
    Authentication authentication = this.authManager.authenticate(auth);
     log.info("auth : " + authentication) ;
      log.warn("auth : " + authentication) ;
     Usuario user = (Usuario) authentication.getPrincipal();
     String token = this.jwtTokenProvider.generateToken(authentication);
     log.info("respuesta : " + user) ;
      return new LoginResponse(user.getUsername(),
                user.getRol().getNombre(),
                token);
        }
       catch (Exception e) {
            log.error("Error en la autenticación: {}", e.getMessage());
            return new LoginResponse("Error en la autenticación", null, null);
       }
   }

}
