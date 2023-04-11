package com.ecommerce.ecommerce.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerce.dto.ProductoDTO;
import com.ecommerce.ecommerce.entidad.Producto;

public class ProductoMapper {

  public static ProductoDTO toDTO(Producto producto) {
    ProductoDTO productoDTO = new ProductoDTO();
    productoDTO.setCantidad(producto.getCantidad());
    productoDTO.setDescripcion(producto.getDescripcion());
    productoDTO.setId(producto.getId());
    productoDTO.setImagen(producto.getImagen());
    productoDTO.setNombre(producto.getNombre());
    productoDTO.setActivo(producto.getActivo());
    productoDTO.setUsuario(UsuarioMapper.toDTO(producto.getUsuario()));
    return productoDTO;
  }

  public static List<ProductoDTO> toDTOList(List<Producto> productos) {
    List<ProductoDTO> productosDTO = new ArrayList<>();
    for (Producto producto : productos) {
      productosDTO.add(toDTO(producto));
    }
    return productosDTO;
  }

  public static Producto toEntity(ProductoDTO productoDTO) {
    Producto producto = new Producto();
    producto.setCantidad(productoDTO.getCantidad());
    producto.setDescripcion(productoDTO.getDescripcion());
    producto.setId(productoDTO.getId());
    producto.setImagen(productoDTO.getImagen());
    producto.setNombre(productoDTO.getNombre());
    producto.setActivo(productoDTO.getActivo());
    producto.setUsuario(UsuarioMapper.toEntity(productoDTO.getUsuario()));
    return producto;
  }

  public static List<Producto> toEntityList(List<ProductoDTO> productosDTO) {
    List<Producto> productos = new ArrayList<>();
    for (ProductoDTO productoDTO : productosDTO) {
      productos.add(toEntity(productoDTO));
    }
    return productos;
  }

}