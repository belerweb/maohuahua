package com.belerweb.maohuahua.service;

import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
  private static final String DOMAIN = ATOM + "+(\\." + ATOM + "+)+";
  private static final Pattern EMAIL_PATTERN = Pattern.compile("^" + ATOM + "+(\\." + ATOM + "+)*@"
      + DOMAIN + "$", Pattern.CASE_INSENSITIVE);

  private String host = System.getProperty("email.host");
  private int port = Integer.parseInt(System.getProperty("email.port"));
  private boolean ssl = Boolean.parseBoolean(System.getProperty("email.ssl"));
  private String username = System.getProperty("email.username");
  private String password = System.getProperty("email.password");
  private String from = System.getProperty("email.from");

  public boolean isValidEmail(String email) {
    return EMAIL_PATTERN.matcher(email).matches();
  }

  public boolean send(String to, String subject, String content) {
    try {
      HtmlEmail htmlEmail = new HtmlEmail();
      htmlEmail.setCharset("UTF-8");
      htmlEmail.setSSLOnConnect(ssl);
      htmlEmail.setHostName(host);
      htmlEmail.setSmtpPort(port);
      htmlEmail.setAuthentication(username, password);
      htmlEmail.setFrom(from, "猫画画管理员");

      htmlEmail.addTo(to);
      htmlEmail.setSubject(subject);
      htmlEmail.setHtmlMsg(content);
      htmlEmail.send();
      return true;
    } catch (EmailException e) {
      e.printStackTrace();
      return false;
    }
  }

}
