/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.config;

import com.gestion.controller.UsuarioController;
import com.gestion.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.Jwks;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecretJwk;
import java.util.Date;
import javax.crypto.SecretKey;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtTokenProvider {
     private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);


    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Value("${app.security.jwt.expiration}")
    private Long jwtDurationSeconds;

//SecretKey key = Jwts.SIG.HS256.key().build();


    public String generateToken(Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
//logger.info("controlador " + key);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .setHeaderParam("typ", "JWT")
                .setSubject(Long.toString(user.getIdUsuario()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtDurationSeconds * 1000)))
                .claim("username", user.getUsername())
                .claim("rol", user.getRol().getNombre())
                .claim("foto", user.getFoto())
                .compact();

    }

    public boolean isValidToken(String token) {
        if (!StringUtils.hasLength(token))
            return false;

        try {
            
            JwtParser validator = Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .build();

            validator.parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            logger.info("Token incorrecto", (Object) e);
        } catch (ExpiredJwtException e) {
            logger.info("Token expirado", e);
        }
        return false;

    }

     public String getUsernameFromToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build();

        Claims claims = parser.parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }
}
