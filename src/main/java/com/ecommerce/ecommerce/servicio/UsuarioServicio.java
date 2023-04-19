package com.ecommerce.ecommerce.servicio;

import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.entidad.Usuario;

public interface UsuarioServicio {

  UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);

  String registroTokenUsuario(Usuario usuario);

  String confirmacionToken(String token);

  void buscarProEmail(String email);

}
