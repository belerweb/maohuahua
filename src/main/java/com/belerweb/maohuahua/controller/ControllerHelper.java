package com.belerweb.maohuahua.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;

import com.belerweb.maohuahua.model.User;
import com.belerweb.maohuahua.service.UserService;
import com.belerweb.maohuahua.view.ViewHelper;


public abstract class ControllerHelper extends ViewHelper {

  @Autowired
  protected UserService userService;

  protected User retrieveSiteOwner(HttpServletRequest request) {
    String host = request.getServerName();
    String subdomain = "";
    if (host.length() > 14) {
      subdomain = host.substring(0, host.length() - 14);
    }

    return userService.getUser("subDomain", subdomain);
  }

  protected ResponseEntity<Object> ok() {
    return new ResponseEntity<Object>(HttpStatus.OK);
  }

  protected ResponseEntity<Object> ok(String message) {
    return new ResponseEntity<Object>(message, HttpStatus.OK);
  }

  protected ResponseEntity<Object> notFound() {
    return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
  }

  protected ResponseEntity<Object> illegal() {
    return error("非法请求");
  }

  protected ResponseEntity<Object> error(String message) {
    return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
  }

  protected ResponseEntity<Object> json(Object object) {
    try {
      MultiValueMap<String, String> headers = new HttpHeaders();
      headers.add("Content-Type", "application/json; charset=utf-8");
      return new ResponseEntity<Object>(new ObjectMapper().writeValueAsString(object), headers,
          HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  protected User getUser() {
    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Method getDetail = principal.getClass().getMethod("getDetail");
      return (User) getDetail.invoke(principal);
    } catch (Exception e) {
      return null;
    }
  }
}
