package com.ecommerce.ecommerce.jwt;

public class JwtResp {
  private String token;

  public JwtResp(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
