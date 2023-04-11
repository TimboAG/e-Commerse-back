package com.ecommerce.ecommerce.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalControlador {

  @GetMapping("/")
  public String inicio() {
    return "Hola mundo!";
  }
}
