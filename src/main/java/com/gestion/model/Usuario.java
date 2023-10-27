/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.model;

import jakarta.persistence.*;
import java.io.Serializable;

import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    
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
     private String contraseña;
     private String foto;
     
      @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol")
    private Rol rol;
    
    
}
