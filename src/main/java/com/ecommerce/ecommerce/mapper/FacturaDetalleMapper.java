package com.ecommerce.ecommerce.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerce.dto.FacturaDetalleDTO;
import com.ecommerce.ecommerce.entidad.FacturaDetalle;

public class FacturaDetalleMapper {
  public static FacturaDetalleDTO toDTO(FacturaDetalle facturaDetalle) {
    FacturaDetalleDTO facturaDetalleDTO = new FacturaDetalleDTO();
    facturaDetalleDTO.setCantidad(facturaDetalle.getCantidad());
    facturaDetalleDTO.setId(facturaDetalle.getId());
    facturaDetalleDTO.setNombre(facturaDetalle.getNombre());
    facturaDetalleDTO.setPrecio(facturaDetalle.getPrecio());
    facturaDetalleDTO.setProducto(ProductoMapper.toDTO(facturaDetalle.getProducto()));
    facturaDetalleDTO.setFactura(FacturaMapper.toDTO(facturaDetalle.getFactura()));
    facturaDetalleDTO.setTotalDetalle(facturaDetalle.getTotalDetalle());
    return facturaDetalleDTO;
  }

  public static List<FacturaDetalleDTO> toDTOList(List<FacturaDetalle> facturaDetalles) {
    List<FacturaDetalleDTO> facturaDetalleDTOs = new ArrayList<>();
    for (FacturaDetalle facturaDetalle : facturaDetalles) {
      facturaDetalleDTOs.add(toDTO(facturaDetalle));
    }
    return facturaDetalleDTOs;
  }

  public static FacturaDetalle toEntity(FacturaDetalleDTO facturaDetalleDTO) {
    FacturaDetalle facturaDetalle = new FacturaDetalle();
    facturaDetalle.setCantidad(facturaDetalleDTO.getCantidad());
    facturaDetalle.setId(facturaDetalleDTO.getId());
    facturaDetalle.setNombre(facturaDetalleDTO.getNombre());
    facturaDetalle.setPrecio(facturaDetalleDTO.getPrecio());
    facturaDetalle.setProducto(ProductoMapper.toEntity(facturaDetalleDTO.getProducto()));
    facturaDetalle.setFactura(FacturaMapper.toEntity(facturaDetalleDTO.getFactura()));
    facturaDetalle.setTotalDetalle(facturaDetalleDTO.getTotalDetalle());
    return facturaDetalle;
  }

  public static List<FacturaDetalle> toEntityList(List<FacturaDetalleDTO> facturaDetalleDTOs) {
    List<FacturaDetalle> facturaDetalles = new ArrayList<>();
    for (FacturaDetalleDTO facturaDetalleDTO : facturaDetalleDTOs) {
      facturaDetalles.add(toEntity(facturaDetalleDTO));
    }
    return facturaDetalles;
  }

}
