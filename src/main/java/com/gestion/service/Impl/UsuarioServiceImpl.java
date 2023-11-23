/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.service.Impl;


import com.gestion.config.PasswordEncoderConfig;
import com.gestion.model.Rol;
import com.gestion.model.Usuario;
import com.gestion.repository.UsuarioRepository;
import com.gestion.service.UsuarioService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioServiceImpl implements UsuarioService {
@Autowired
private UsuarioRepository usuarioRepository;
 @Autowired
    private PasswordEncoder passwordEncoder;

private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Override
    public Usuario añadirUsuario(Usuario usuario, Rol rol) {
        logger.debug("Entrando al método añadirUsuario");
        
         Usuario usuarioCreado = asignarRolAUsuario(usuario, rol);
         usuarioCreado.setPassword(passwordEncoder.encode(usuario.getPassword()));
        logger.info("Entrando al método añadirUsuario  " + usuarioCreado);
        logger.debug("Entrando al método añadirUsuario " + usuarioCreado);
        
       return usuarioRepository.save(usuarioCreado);
      
       
       
    }

    @Override
    public void eliminarUsuario(long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario editarUsuario(Usuario usuario, long id) {
        Usuario usuarioEditar = usuarioRepository.findById(id);
        if(usuario.getNombre()!= null){
            usuarioEditar.setNombre(usuario.getNombre());
        }
         if(usuario.getApellidos()!= null){
            usuarioEditar.setApellidos(usuario.getApellidos());
        }
          if(usuario.getEmail()!= null){
            usuarioEditar.setEmail(usuario.getEmail());
        }
           if(usuario.getTelefono()!= null){
            usuarioEditar.setTelefono(usuario.getTelefono());
        }
            if(usuario.getPassword()!= null){
            usuarioEditar.setPassword(usuario.getPassword());
        }
             if(usuario.getFoto()!= null){
            usuarioEditar.setFoto(usuario.getFoto());
        }
             usuarioRepository.save(usuarioEditar);
             return usuarioEditar;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario asignarRolAUsuario(Usuario usuario, Rol rol) {
        usuario.setRol(rol);
         return usuario;
    }

    @Override
    public Usuario findByUsername(String Uusario) {
        return this.usuarioRepository.findByUsername(Uusario);
    }
    
    
    
}
