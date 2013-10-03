package com.belerweb.maohuahua.service;

import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private CentralConfig centralConfig;

  private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
  private static final String DOMAIN = ATOM + "+(\\." + ATOM + "+)+";
  private static final Pattern EMAIL_PATTERN = Pattern.compile("^" + ATOM + "+(\\." + ATOM + "+)*@"
      + DOMAIN + "$", Pattern.CASE_INSENSITIVE);

  public boolean isValidEmail(String email) {
    return EMAIL_PATTERN.matcher(email).matches();
  }

  public boolean send(String to, String subject, String content) {
    try {
      HtmlEmail htmlEmail = newEmail();
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

  private HtmlEmail newEmail() throws EmailException {
    HtmlEmail htmlEmail = new HtmlEmail();
    htmlEmail.setCharset("UTF-8");
    htmlEmail.setSSLOnConnect(Boolean.valueOf(centralConfig.get(CentralConfig.EMAIL_SSL)));
    htmlEmail.setHostName(centralConfig.get(CentralConfig.EMAIL_HOST));
    htmlEmail.setSmtpPort(Integer.valueOf(centralConfig.get(CentralConfig.EMAIL_POST)));
    htmlEmail.setAuthentication(centralConfig.get(CentralConfig.EMAIL_USERNAME),
        centralConfig.get(CentralConfig.EMAIL_PASSWORD));
    htmlEmail.setFrom(centralConfig.get(CentralConfig.EMAIL_FROM),
        centralConfig.get(CentralConfig.EMAIL_FROM_NAME));
    return htmlEmail;
  }

}
