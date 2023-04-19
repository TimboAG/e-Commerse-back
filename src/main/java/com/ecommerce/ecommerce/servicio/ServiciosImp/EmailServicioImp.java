package com.ecommerce.ecommerce.servicio.ServiciosImp;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.servicio.EmailServicio;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServicioImp implements EmailServicio {

  private final JavaMailSender javaMailSender;
  private final static Logger LOGGER = LoggerFactory.getLogger(EmailServicioImp.class);

  @Async
  @Override
  public void send(String to, String email) {
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
      helper.setText(email, true);
      helper.setTo(to);
      helper.setSubject("Confirme su email");
      helper.setFrom("no-reply@e-commerce.com");
      javaMailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("fallo en el envio del email", e);
      throw new IllegalStateException("fallo en el envio del email");
    }
  }
}