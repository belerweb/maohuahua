package com.belerweb.maohuahua.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class User implements Serializable {

  private static final long serialVersionUID = 9125098466490195551L;

  private String id;
  private String mobile;// 手机号
  private String email;// 电子邮件
  private String username;// 用户名
  private String password;// 密码
  private Boolean needChangePwd = false;// 需要修改密码
  private Boolean mobileValid = false;// 手机号已验证
  private Boolean emailValid = false;// 电子邮件已验证
  private String subDomain;// TODO DELETE 子域名
  private String fullname;// 姓名
  private String nickname;// 昵称
  private String avatar;// 头像
  private String source;// 注册来源
  private String lastLoginIP;// 最后登录IP
  private Date created;// 注册时间
  private Date modified;// 最后更新时间
  private List<String> roles = new ArrayList<>();// 角色

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getLastLoginIP() {
    return lastLoginIP;
  }

  public void setLastLoginIP(String lastLoginIP) {
    this.lastLoginIP = lastLoginIP;
  }

  public Boolean getNeedChangePwd() {
    return needChangePwd;
  }

  public void setNeedChangePwd(Boolean needChangePwd) {
    this.needChangePwd = needChangePwd;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public List<SimpleGrantedAuthority> getWrapperRoles() {
    List<SimpleGrantedAuthority> roles = new ArrayList<>();
    for (String role : this.roles) {
      roles.add(new SimpleGrantedAuthority(role));
    }
    return roles;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public Boolean getMobileValid() {
    return mobileValid;
  }

  public void setMobileValid(Boolean mobileValid) {
    this.mobileValid = mobileValid;
  }

  public Boolean getEmailValid() {
    return emailValid;
  }

  public void setEmailValid(Boolean emailValid) {
    this.emailValid = emailValid;
  }

  public String getSubDomain() {
    return subDomain;
  }

  public void setSubDomain(String subDomain) {
    this.subDomain = subDomain;
  }

  public static class UserWrapper extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = -2466927911565414318L;
    private User user;

    public UserWrapper(String username, User user) {
      super(username, user.getPassword(), user.getWrapperRoles());
      this.user = user;
    }

    public User getDetail() {
      return user;
    }
  }

}
