package com.ecommerce.ecommerce.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entidad.FacturaDetalle;

@Repository
public interface FacturaDetalleRepositorio extends JpaRepository<FacturaDetalle, Integer> {

}
