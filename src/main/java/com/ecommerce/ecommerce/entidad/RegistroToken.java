package com.ecommerce.ecommerce.entidad;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token")
public class RegistroToken {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String token;
  private LocalDateTime creado;
  private LocalDateTime expirado;
  private LocalDateTime confirmado;
  @ManyToOne
  @JoinColumn
  private Usuario usuario;

  public RegistroToken(String token, LocalDateTime creado, LocalDateTime expirado, Usuario usuario) {
    this.token = token;
    this.creado = creado;
    this.expirado = expirado;
    this.usuario = usuario;
  }
}
