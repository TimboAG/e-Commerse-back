package com.ecommerce.ecommerce.servicio;

import com.ecommerce.ecommerce.entidad.RegistroToken;

public interface RegistroTokenServicio {

  RegistroToken save(RegistroToken token);

  RegistroToken getToken(String token);

  int setConfirmedAt(String token);
}
