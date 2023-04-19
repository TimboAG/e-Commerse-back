package com.ecommerce.ecommerce.servicio.ServiciosImp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ecommerce.ecommerce.entidad.Usuario;
import com.ecommerce.ecommerce.repositorio.UsuarioRepositorio;
import com.ecommerce.ecommerce.servicio.DetalleUsuario;

@Service
public class DetalleusuarioImp implements DetalleUsuario {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
    if (usuario != null) {
      List<GrantedAuthority> permisos = new ArrayList<>();
      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
      permisos.add(p);
      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = attr.getRequest().getSession(true);
      session.setAttribute("usuariosession", usuario);
      return new User(usuario.getEmail(), usuario.getPassword(), permisos);
    } else {
      throw new UsernameNotFoundException("El usuario no existe");
    }
  }

}
