/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {
@Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UserDetailsService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.extractToken(request);

        if (this.tokenProvider.isValidToken(token)) {
            String username = this.tokenProvider.getUsernameFromToken(token);
            UserDetails user = this.userService.loadUserByUsername(username);
            
          Authentication auth = new UsernamePasswordAuthenticationToken(
                  user.getUsername(),
                  user.getPassword(),
                  user.getAuthorities());
          
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authoritation");
        if(StringUtils.hasLength(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring("bearer ".length());
        }
        return null;
    }
    
  
}
