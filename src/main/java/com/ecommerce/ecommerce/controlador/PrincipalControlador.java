package com.ecommerce.ecommerce.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.UsuarioDTO;

@RestController
public class PrincipalControlador {

  @GetMapping("/")
  public String inicio() {
    return "Hola mundo!";
  }

  @GetMapping(path = "/login", consumes = "application/json")
  public ResponseEntity<String> login() {
    try {
      return new ResponseEntity<>("exito", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
  }
}
