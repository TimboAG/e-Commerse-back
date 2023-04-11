package com.ecommerce.ecommerce.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entidad.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

}
