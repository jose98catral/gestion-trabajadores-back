/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.controller;

import com.gestion.model.Rol;
import com.gestion.model.Usuario;
import com.gestion.service.RolService;
import com.gestion.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private  ObjectMapper objectMapper;
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;
    
      private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    
  @PostMapping("/guardar")
public ResponseEntity<?> añadirUsuario(@RequestBody  Map<String, Object> requestBody) {
    try {
        Usuario usuario = objectMapper.convertValue(requestBody.get("usuario"), Usuario.class);
        Rol rol = objectMapper.convertValue(requestBody.get("rol"), Rol.class);
        Rol rolAñadir = rolService.findById(rol.getIdRol());
        
        logger.info("controlador " + rolAñadir);
        logger.info("usuario " + usuario);
       
       Usuario usuarioCreado = usuarioService.añadirUsuario(usuario, rolAñadir);
        return new ResponseEntity<>("Usuario añadido " + usuarioCreado, HttpStatus.CREATED);
    } catch (NumberFormatException e) {
       
        // Manejo de error si el ID del rol no es un número válido
        return new ResponseEntity<>("ID de rol no válido", HttpStatus.BAD_REQUEST);
    }
}
    
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarUsuario(@RequestParam String id){
        
        long idBorrar = Long.parseLong(id);
        
        usuarioService.eliminarUsuario(idBorrar);
        
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
        
        
    }
    
    @PutMapping("/editar")
    
    public ResponseEntity<?> editarUsuario(@RequestBody Usuario usuario, @RequestParam String id){
        
        long idEditar = Long.parseLong(id);
        logger.info("el id es; " + idEditar);
        
        Usuario usuarioEditado = usuarioService.editarUsuario(usuario, idEditar);
        
        return new ResponseEntity<>("Usuario editado: " + usuarioEditado , HttpStatus.OK);
        
        
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List <Usuario> usuarios = new ArrayList();
        
        usuarios = usuarioService.listarUsuarios();
        
        return new ResponseEntity<>(usuarios,HttpStatus.OK);
    }
}
