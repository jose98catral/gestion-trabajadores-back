/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name="usuario")
public class Usuario implements Serializable,UserDetails {
    
    private static final long serialVersionUID= 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private long idUsuario;
     @Column(name="usuario")
    private String username;
    
     private String nombre;
     private String apellidos;
     private String telefono;
     private String email;
     private String password;
     private String foto;
     
      @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return Collections.singleton(new SimpleGrantedAuthority(this.rol.getNombre()));
    }

    @Override
    public boolean isAccountNonExpired() {
    return true;
    }

    @Override
    public boolean isAccountNonLocked() {
   return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
   return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    
}
