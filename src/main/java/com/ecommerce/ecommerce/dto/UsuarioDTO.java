package com.ecommerce.ecommerce.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.ecommerce.enumeracion.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UsuarioDTO {
  private Integer id;
  private String nombre;
  private String nombreUsuario;
  private String email;
  private String direccion;
  private String telefono;
  private String password;
  private String token;
  private Boolean activo;
  private Rol rol;
  private List<ProductoDTO> productos;
  private List<FacturaDTO> facturas;
}