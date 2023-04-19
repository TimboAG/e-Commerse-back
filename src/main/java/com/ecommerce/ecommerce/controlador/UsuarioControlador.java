package com.ecommerce.ecommerce.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.jwt.JwtResp;
import com.ecommerce.ecommerce.jwt.JwtToken;
import com.ecommerce.ecommerce.servicio.UsuarioServicio;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

  @Autowired
  private UsuarioServicio usuarioServicio;
  @Autowired
  private JwtToken jwtToken;
  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/registro")
  public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
    UsuarioDTO usuarioRegistrado = usuarioServicio.registrarUsuario(usuarioDTO);
    String token = jwtToken.generarToken(usuarioRegistrado.getEmail());
    JwtResp response = new JwtResp(token);
    System.out.println("Token: " + token);
    System.out.println("Response: " + response);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    // return new ResponseEntity<>(usuarioRegistrado, HttpStatus.CREATED);
  }

  @GetMapping(path = "confirmacion")
  public String confirmacionToken(@RequestParam("token") String token) {
    return usuarioServicio.confirmacionToken(token);
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUsuario(@RequestBody UsuarioDTO usuarioDTO) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtToken.generarToken(authentication.getName());
    return ResponseEntity.ok(new JwtResp(token));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logoutUsuario(HttpServletRequest request, HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
    return ResponseEntity.ok("Usuario cerro sesión exitosamente");
  }

}
