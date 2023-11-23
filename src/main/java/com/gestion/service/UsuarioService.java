/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gestion.service;

import com.gestion.model.Rol;
import com.gestion.model.Usuario;
import java.util.List;


public interface UsuarioService {
    public Usuario añadirUsuario(Usuario usuario, Rol rol);
    
    public void eliminarUsuario(long id);
    
    public Usuario editarUsuario(Usuario usuario, long id);
    
    public List<Usuario> listarUsuarios();
    
    public Usuario asignarRolAUsuario(Usuario usuario, Rol rol);
    
    public Usuario findByUsername(String username);
}
