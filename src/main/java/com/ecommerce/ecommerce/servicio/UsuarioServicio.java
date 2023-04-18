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

  @Autowired
  private EmailServicio emailServicio;

  @Transactional
  public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
    Usuario usuario = UsuarioMapper.toEntityRegistro(usuarioDTO);
    Usuario usuarioEmail = usuarioRepositorio.buscarPorEmail(usuario.getEmail());
    if (usuarioEmail == null) {
      usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
      String token = registroTokenUsuario(usuario);
      usuario.setRol(Rol.CLIENTE);
      usuario.setActivo(false);
      Usuario usuarioRegistrado = usuarioRepositorio.save(usuario);
      String link = "http://localhost:8080/usuario/confirmacion?token=" + token;
      emailServicio.send(usuario.getEmail(), enviarEmail(usuario.getNombre(),
          link));
      return UsuarioMapper.toDTORegistro(usuarioRegistrado);
    } else {
      throw new IllegalStateException("email ya existe");
    }
  }

  public String registroTokenUsuario(Usuario usuario) {
    String token = UUID.randomUUID().toString();
    RegistroToken registroToken = new RegistroToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15), usuario);
    registroTokenServicio.save(registroToken);
    return token;
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
    Usuario miUsuario = registroToken.getUsuario();
    miUsuario.setActivo(true);
    miUsuario.setToken(token);
    return "confirmado";
  }

  public void buscarProEmail(String email) {
    usuarioRepositorio.buscarPorEmail(email);
  }

  private String enviarEmail(String name, String link) {
    return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
        "\n" +
        "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
        "\n" +
        "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
        +
        "    <tbody><tr>\n" +
        "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
        "        \n" +
        "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
        +
        "          <tbody><tr>\n" +
        "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
        "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
        +
        "                  <tbody><tr>\n" +
        "                    <td style=\"padding-left:10px\">\n" +
        "                  \n" +
        "                    </td>\n" +
        "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
        "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
        +
        "                    </td>\n" +
        "                  </tr>\n" +
        "                </tbody></table>\n" +
        "              </a>\n" +
        "            </td>\n" +
        "          </tr>\n" +
        "        </tbody></table>\n" +
        "        \n" +
        "      </td>\n" +
        "    </tr>\n" +
        "  </tbody></table>\n" +
        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
        +
        "    <tbody><tr>\n" +
        "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
        "      <td>\n" +
        "        \n" +
        "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
        +
        "                  <tbody><tr>\n" +
        "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
        "                  </tr>\n" +
        "                </tbody></table>\n" +
        "        \n" +
        "      </td>\n" +
        "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
        "    </tr>\n" +
        "  </tbody></table>\n" +
        "\n" +
        "\n" +
        "\n" +
        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
        +
        "    <tbody><tr>\n" +
        "      <td height=\"30\"><br></td>\n" +
        "    </tr>\n" +
        "    <tr>\n" +
        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
        "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
        +
        "        \n" +
        "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name
        + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
        + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
        "        \n" +
        "      </td>\n" +
        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
        "    </tr>\n" +
        "    <tr>\n" +
        "      <td height=\"30\"><br></td>\n" +
        "    </tr>\n" +
        "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
        "\n" +
        "</div></div>";
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
