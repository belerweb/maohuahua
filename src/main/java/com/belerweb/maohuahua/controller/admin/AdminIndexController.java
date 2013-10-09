package com.belerweb.maohuahua.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.belerweb.maohuahua.controller.ControllerHelper;

@Controller
public class AdminIndexController extends ControllerHelper {

  @RequestMapping("/admin")
  public Object home(Model model) {
    return "/v1/admin";
  }

}
