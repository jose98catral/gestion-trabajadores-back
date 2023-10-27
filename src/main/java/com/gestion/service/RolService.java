/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gestion.service;

import com.gestion.model.Rol;
import java.util.List;

/**
 *
 * @author jose9
 */
public interface RolService {
      Rol findById(Long id);
    List<Rol> listarRoles();
    Rol añadirRol(Rol rol);
    void eliminarRol(Long id);
    Rol editarRol(Rol rol, Long id);
}
