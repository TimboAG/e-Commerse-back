package com.ecommerce.ecommerce.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerce.dto.FacturaDTO;
import com.ecommerce.ecommerce.entidad.Factura;

public class FacturaMapper {
  public static FacturaDTO toDTO(Factura factura) {
    FacturaDTO facturaDTO = new FacturaDTO();
    facturaDTO.setFechaCreacion(factura.getFechaCreacion());
    facturaDTO.setFechaRecibida(factura.getFechaRecibida());
    facturaDTO.setId(factura.getId());
    facturaDTO.setNumero(factura.getNumero());
    facturaDTO.setTotal(factura.getTotal());
    facturaDTO.setUsuario(UsuarioMapper.toDTO(factura.getUsuario()));
    facturaDTO.setFacturaDetalle(FacturaDetalleMapper.toDTO(factura.getFacturaDetalle()));
    return facturaDTO;
  }

  public static List<FacturaDTO> toDTOList(List<Factura> facturas) {
    List<FacturaDTO> facturaDTO = new ArrayList<>();
    for (Factura factura : facturas) {
      facturaDTO.add(toDTO(factura));
    }
    return facturaDTO;
  }

  public static Factura toEntity(FacturaDTO facturaDTO) {
    Factura factura = new Factura();
    factura.setFechaCreacion(facturaDTO.getFechaCreacion());
    factura.setFechaRecibida(facturaDTO.getFechaRecibida());
    factura.setId(facturaDTO.getId());
    factura.setNumero(facturaDTO.getNumero());
    factura.setTotal(facturaDTO.getTotal());
    factura.setUsuario(UsuarioMapper.toEntity(facturaDTO.getUsuario()));
    factura.setFacturaDetalle(FacturaDetalleMapper.toEntity(facturaDTO.getFacturaDetalle()));
    return factura;
  }

  public static List<Factura> toEntityList(List<FacturaDTO> facturasDTO) {
    List<Factura> factura = new ArrayList<>();
    for (FacturaDTO facturaDTO : facturasDTO) {
      factura.add(toEntity(facturaDTO));
    }
    return factura;
  }

}