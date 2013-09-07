package com.belerweb.maohuahua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PictureController extends ControllerHelper {

  /**
   * 上传图片
   */
  @RequestMapping(method = RequestMethod.GET, value = "/picture/upload")
  public Object upload() {
    return "/v1/picture/upload";
  }

  /**
   * 上传图片
   */
  @RequestMapping(method = RequestMethod.POST, value = "/picture/upload")
  public Object upload(Model model) {
    return ok();
  }

  /**
   * 我的图片
   */
  @RequestMapping("/picture")
  public Object picture(Model model) {
    return "/v1/picture";
  }

}
