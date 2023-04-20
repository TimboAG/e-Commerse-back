package com.ecommerce.ecommerce.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguracion {
  @Bean
  public JwtInterceptor jwtInterceptor() {
    return new JwtInterceptor();
  }

}
