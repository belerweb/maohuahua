package com.belerweb.maohuahua.model;

import java.io.Serializable;

public class InitializationConfig implements Serializable {

  private static final long serialVersionUID = 6345468052629015221L;

  private String host;
  private Integer port;
  private String dbName;
  private String username;
  private String password;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
