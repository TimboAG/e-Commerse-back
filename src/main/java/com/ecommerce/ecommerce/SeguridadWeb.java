package com.ecommerce.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecommerce.ecommerce.servicio.DetalleUsuario;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = { "com.ecommerce.ecommerce" })
public class SeguridadWeb extends WebSecurityConfigurerAdapter {

  @Autowired
  public DetalleUsuario detalleUsuario;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(detalleUsuario).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN")
        .antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
        .and().formLogin().loginPage("/login")
        .loginProcessingUrl("/verificacionLogin")
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/")
        .permitAll()
        .and().logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .permitAll().and().csrf().disable();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
