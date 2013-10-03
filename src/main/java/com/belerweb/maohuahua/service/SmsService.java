package com.belerweb.maohuahua.service;

import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belerweb.sms._9nuo.Sms;

@Service
public class SmsService implements InitializingBean {

  @Autowired
  private CentralConfig centralConfig;

  private Sms sms;

  public boolean isValidMobile(String mobile) {
    return mobile
        .matches("^(139|138|137|136|135|134|147|150|151|152|157|158|159|182|183|184|187|188|130|131|132|155|156|185|186|145|133|153|180|181|189)\\d{8}$");
  }

  public boolean send(String to, String content) {
    return sms.send(to, content).isSuccess();
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    Properties properties = new Properties();
    properties.put(Sms.PARAM_NAME_USERNAME, centralConfig.get(CentralConfig.NNUO_USERNAME));
    properties.put(Sms.PARAM_NAME_PASSWORD, centralConfig.get(CentralConfig.NNUO_PASSWORD));
    sms = Sms.init(properties);
  }

}
