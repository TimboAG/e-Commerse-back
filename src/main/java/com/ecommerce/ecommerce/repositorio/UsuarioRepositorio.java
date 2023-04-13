package com.ecommerce.ecommerce.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.ecommerce.entidad.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
  @Query("SELECT u FROM Usuario u WHERE u.email = :email")
  public Usuario buscarPorEmail(@Param("email") String email);
}
