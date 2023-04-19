package com.ecommerce.ecommerce.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenImpl implements JwtToken {
  private static final String CLAVE_SECRETA = "miToken-"; // Cambiar por una llave secreta más segura
  private static final long TIEMPO_EXPIRACION = 60 * 60 * 1000; // Tiempo de expiración del token en milisegundos

  private final Algorithm ALGORITMO = Algorithm.HMAC256(CLAVE_SECRETA);

  @Override
  public String generarToken(String email) {
    Date tiempoAhora = new Date();
    Date tiempoExpiracion = new Date(tiempoAhora.getTime() + TIEMPO_EXPIRACION);

    return JWT.create()
        .withSubject(email)
        .withIssuedAt(tiempoAhora)
        .withExpiresAt(tiempoExpiracion)
        .sign(ALGORITMO);
  }

  @Override
  public boolean validarToken(String token, String email) {
    String subject = getSubject(token);
    return (subject.equals(email) && !isTokenExpired(token));
  }

  @Override
  public String getSubject(String token) {
    DecodedJWT jwt = JWT.require(ALGORITMO)
        .build()
        .verify(token);

    return jwt.getSubject();
  }

  private boolean isTokenExpired(String token) {
    DecodedJWT jwt = JWT.require(ALGORITMO)
        .build()
        .verify(token);

    Date expiration = jwt.getExpiresAt();
    return expiration.before(new Date());
  }
}