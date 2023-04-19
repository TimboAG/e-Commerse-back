package com.ecommerce.ecommerce.servicio.ServiciosImp;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.entidad.RegistroToken;
import com.ecommerce.ecommerce.repositorio.RegristroTokenRepositorio;
import com.ecommerce.ecommerce.servicio.RegistroTokenServicio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistroTokenServicioImp implements RegistroTokenServicio {
  @Autowired
  private RegristroTokenRepositorio regristroTokenRepositorio;

  @Override
  public RegistroToken save(RegistroToken token) {
    return regristroTokenRepositorio.save(token);
  }

  @Override
  public RegistroToken getToken(String token) {
    Optional<RegistroToken> tokenFound = regristroTokenRepositorio.findByToken(token);
    return tokenFound.get();
  }

  @Override
  public int setConfirmedAt(String token) {
    return regristroTokenRepositorio.actualizarConfirmado(token, LocalDateTime.now());
  }

  public CrudRepository<RegistroToken, Long> getDao() {
    return null;
  }
}
