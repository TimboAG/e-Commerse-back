package com.ecommerce.ecommerce.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entidad.Factura;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Integer> {

}
