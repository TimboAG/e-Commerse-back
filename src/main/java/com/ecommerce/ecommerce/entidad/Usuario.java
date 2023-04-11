package com.ecommerce.ecommerce.entidad;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerce.ecommerce.enumeracion.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nombre;
  private String nombreUsuario;
  private String email;
  private String direccion;
  private String telefono;
  private String password;
  private String token;
  private Boolean activo;
  @Enumerated(EnumType.STRING)
  private Rol rol;
  @OneToMany(mappedBy = "usuario")
  private List<Producto> productos;
  @OneToMany(mappedBy = "usuario")
  private List<Factura> facturas;
}
