package com.ecommerce.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDetalleDTO {
  private Integer id;
  private String nombre;
  private double cantidad;
  private double precio;
  private double totalDetalle;
  private FacturaDTO factura;
  private ProductoDTO producto;
}
