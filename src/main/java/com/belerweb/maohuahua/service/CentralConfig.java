package com.belerweb.maohuahua.service;

import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;

public class CentralConfig implements InitializingBean {

  private Properties properties;

  private String app;
  private String key;
  private String secret;
  private String profile;

  public String getApp() {
    return app;
  }

  public void setApp(String app) {
    this.app = app;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  public String get(String config) {
    return properties.getProperty(config);
  }

  public String get(String config, String defaultValue) {
    return properties.getProperty(config, defaultValue);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    HttpClient client = new DefaultHttpClient();
    HttpPost post = new HttpPost("https://central.net.in");
    post.setHeader("App", app);
    post.setHeader("App-Key", key);
    post.setHeader("App-Secret", secret);
    post.setHeader("App-Profile", profile);
    HttpResponse response = client.execute(post);
    if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
      throw new RuntimeException("获取配置信息失败。");
    }

    properties = new ObjectMapper().readValue(response.getEntity().getContent(), Properties.class);
  }

}
