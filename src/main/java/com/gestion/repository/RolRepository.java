/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.repository;

import com.gestion.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jose9
 */
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findById(long id);
    
}
