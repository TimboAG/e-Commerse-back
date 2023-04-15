package com.ecommerce.ecommerce.servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.entidad.RegistroToken;
import com.ecommerce.ecommerce.entidad.Usuario;
import com.ecommerce.ecommerce.enumeracion.Rol;
import com.ecommerce.ecommerce.mapper.UsuarioMapper;
import com.ecommerce.ecommerce.repositorio.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Autowired
  private RegistroTokenServicio registroTokenServicio;

  @Transactional
  public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
    Usuario usuario = UsuarioMapper.toEntityRegistro(usuarioDTO);
    Usuario usuarioEmail = usuarioRepositorio.buscarPorEmail(usuario.getEmail());
    if (usuarioEmail == null) {
      usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
      registroTokenUsuario(usuario);
      usuario.setRol(Rol.CLIENTE);
      usuario.setActivo(false);
      Usuario usuarioRegistrado = usuarioRepositorio.save(usuario);
      return UsuarioMapper.toDTORegistro(usuarioRegistrado);
    } else {
      throw new IllegalStateException("email ya existe");
    }
  }

  public void registroTokenUsuario(Usuario usuario) {
    String token = UUID.randomUUID().toString();
    RegistroToken registroToken = new RegistroToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15), usuario);
    registroTokenServicio.save(registroToken);
  }

  @Transactional
  public String confirmacionToken(String token) {
    RegistroToken registroToken = registroTokenServicio.getToken(token);

    if (registroToken == null) {
      throw new IllegalStateException("token not found");
    }

    if (registroToken.getConfirmado() != null) {
      throw new IllegalStateException("el email ya fue confirmado");
    }

    if (registroToken.getExpirado().isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("token expiro");
    }

    registroTokenServicio.setConfirmedAt(token);
    buscarProEmail(registroToken.getUsuario().getEmail());

    return "confirmado";
  }

  public void buscarProEmail(String email) {
    usuarioRepositorio.buscarPorEmail(email);
  }

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
      return null;
    }
  }
}
