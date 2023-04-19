package com.ecommerce.ecommerce.servicio;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface DetalleUsuario extends UserDetailsService {
  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
