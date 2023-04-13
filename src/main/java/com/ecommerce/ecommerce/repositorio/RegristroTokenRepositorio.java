package com.ecommerce.ecommerce.repositorio;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.ecommerce.entidad.RegistroToken;

public interface RegristroTokenRepositorio extends JpaRepository<RegistroToken, Long> {
  @Query("SELECT a FROM RegistroToken a WHERE a.token = :token")
  Optional<RegistroToken> findByToken(@Param("token") String token);

  @Query("UPDATE RegistroToken a SET a.confirmado = :confirmado WHERE a.token = :token")
  @Modifying
  int actualizarConfirmado(@Param("token") String token, @Param("confirmado") LocalDateTime confirmedAt);
}
