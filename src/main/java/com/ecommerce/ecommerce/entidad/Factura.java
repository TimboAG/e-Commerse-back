package com.ecommerce.ecommerce.entidad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "factura")
public class Factura {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String numero;
  @Temporal(TemporalType.DATE)
  private Date fechaCreacion;
  @Temporal(TemporalType.DATE)
  private Date fechaRecibida;
  private double total;
  @ManyToOne
  private Usuario usuario;
  @OneToOne(mappedBy = "factura")
  private FacturaDetalle facturaDetalle;
}
