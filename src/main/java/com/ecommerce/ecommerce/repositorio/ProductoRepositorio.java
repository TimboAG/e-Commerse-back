package com.ecommerce.ecommerce.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entidad.Producto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

}
