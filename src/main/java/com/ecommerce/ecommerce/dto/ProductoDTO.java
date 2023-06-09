package com.ecommerce.ecommerce.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductoDTO {
  private Integer id;
  private String nombre;
  private String descripcion;
  private int cantidad;
  private double precio;
  private String imagen;
  private UsuarioDTO usuario;
  private Boolean activo;

}