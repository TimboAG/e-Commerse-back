package com.ecommerce.ecommerce.jwt;

public interface JwtToken {
  String generarToken(String email);

  boolean validarToken(String token, String email);

  String getSubject(String token);

  boolean isTokenExpired(String token);
}
