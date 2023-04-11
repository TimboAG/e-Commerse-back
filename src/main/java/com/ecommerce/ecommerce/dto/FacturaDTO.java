package com.ecommerce.ecommerce.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {
  private Integer id;
  private String numero;
  private Date fechaCreacion;
  private Date fechaRecibida;
  private double total;
  private UsuarioDTO usuario;
  private FacturaDetalleDTO facturaDetalle;
}