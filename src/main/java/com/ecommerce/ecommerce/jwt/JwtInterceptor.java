package com.ecommerce.ecommerce.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class JwtInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  @Lazy
  private JwtToken jwtToken;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String token = request.getHeader("Authorization");

    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
      if (jwtToken.isTokenExpired(token)) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
          new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.sendRedirect("/usuario/login");
        return false;
      }
      return true;
    } else {
      response.sendRedirect("/usuario/login");
      return false;
    }
  }
}