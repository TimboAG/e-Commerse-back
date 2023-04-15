package com.ecommerce.ecommerce.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.servicio.UsuarioServicio;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

  @Autowired
  private UsuarioServicio usuarioServicio;

  @PostMapping("/registro")
  public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
    UsuarioDTO usuarioRegistrado = usuarioServicio.registrarUsuario(usuarioDTO);
    return new ResponseEntity<>(usuarioRegistrado, HttpStatus.CREATED);
  }

  @GetMapping("/confirmacion")
  public String confirmacionToken(@RequestParam("token") String token) {
    return usuarioServicio.confirmacionToken(token);
  }

}
