package com.belerweb.maohuahua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController extends ControllerHelper {


  @RequestMapping("/site/domain")
  public Object domain(Model model) {
    model.addAttribute("user", getUser());
    return "/v1/site/domain";
  }


}
