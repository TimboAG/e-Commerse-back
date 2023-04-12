package com.ecommerce.ecommerce.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.entidad.Usuario;

public class UsuarioMapper {

  public static UsuarioDTO toDTO(Usuario usuario) {
    UsuarioDTO usuarioDTO = new UsuarioDTO();
    usuarioDTO.setActivo(usuario.getActivo());
    usuarioDTO.setDireccion(usuario.getDireccion());
    usuarioDTO.setEmail(usuario.getEmail());
    usuarioDTO.setFacturas(FacturaMapper.toDTOList(usuario.getFacturas()));
    usuarioDTO.setId(usuario.getId());
    usuarioDTO.setNombre(usuario.getNombre());
    usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
    usuarioDTO.setPassword(usuario.getPassword());
    usuarioDTO.setProductos(ProductoMapper.toDTOList(usuario.getProductos()));
    usuarioDTO.setRol(usuario.getRol());
    usuarioDTO.setTelefono(usuario.getTelefono());
    usuarioDTO.setToken(usuario.getToken());
    return usuarioDTO;
  }

  public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
    List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
    for (Usuario usuario : usuarios) {
      usuarioDTOs.add(toDTO(usuario));
    }
    return usuarioDTOs;
  }

  public static Usuario toEntity(UsuarioDTO usuarioDTO) {
    Usuario usuario = new Usuario();
    usuario.setActivo(usuarioDTO.getActivo());
    usuario.setDireccion(usuarioDTO.getDireccion());
    usuario.setEmail(usuarioDTO.getEmail());
    usuario.setFacturas(FacturaMapper.toEntityList(usuarioDTO.getFacturas()));
    usuario.setId(usuarioDTO.getId());
    usuario.setNombre(usuarioDTO.getNombre());
    usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
    usuario.setPassword(usuarioDTO.getPassword());
    usuario.setProductos(ProductoMapper.toEntityList(usuarioDTO.getProductos()));
    usuario.setRol(usuarioDTO.getRol());
    usuario.setTelefono(usuarioDTO.getTelefono());
    usuario.setToken(usuarioDTO.getToken());
    return usuario;
  }

  public static List<Usuario> toEntityList(List<UsuarioDTO> usuarioDTOs) {
    List<Usuario> usuarios = new ArrayList<>();
    for (UsuarioDTO usuarioDTO : usuarioDTOs) {
      usuarios.add(toEntity(usuarioDTO));
    }
    return usuarios;
  }

  public static UsuarioDTO toDTORegistro(Usuario usuario) {
    UsuarioDTO usuarioDTO = new UsuarioDTO();
    usuarioDTO.setEmail(usuario.getEmail());
    usuarioDTO.setNombre(usuario.getNombre());
    usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
    usuarioDTO.setPassword(usuario.getPassword());
    return usuarioDTO;
  }

  public static List<UsuarioDTO> toDTOListRegistro(List<Usuario> usuarios) {
    List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
    for (Usuario usuario : usuarios) {
      usuarioDTOs.add(toDTORegistro(usuario));
    }
    return usuarioDTOs;
  }

  public static Usuario toEntityRegistro(UsuarioDTO usuarioDTO) {
    Usuario usuario = new Usuario();
    usuario.setEmail(usuarioDTO.getEmail());
    usuario.setNombre(usuarioDTO.getNombre());
    usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
    usuario.setPassword(usuarioDTO.getPassword());
    return usuario;
  }

  public static List<Usuario> toEntityListRegistro(List<UsuarioDTO> usuarioDTOs) {
    List<Usuario> usuarios = new ArrayList<>();
    for (UsuarioDTO usuarioDTO : usuarioDTOs) {
      usuarios.add(toEntityRegistro(usuarioDTO));
    }
    return usuarios;
  }

}