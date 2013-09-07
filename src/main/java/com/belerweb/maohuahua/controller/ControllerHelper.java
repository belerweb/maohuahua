package com.belerweb.maohuahua.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.belerweb.maohuahua.view.ViewHelper;


public abstract class ControllerHelper extends ViewHelper {

  protected ResponseEntity<Object> ok() {
    return new ResponseEntity<Object>(HttpStatus.OK);
  }

  protected ResponseEntity<Object> ok(String message) {
    return new ResponseEntity<Object>(message, HttpStatus.OK);
  }

  protected ResponseEntity<Object> notFound() {
    return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
  }

  protected ResponseEntity<Object> error(String message) {
    return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
  }
}
