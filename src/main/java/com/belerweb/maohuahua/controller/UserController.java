package com.belerweb.maohuahua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.belerweb.maohuahua.model.User;
import com.belerweb.maohuahua.service.UserService;

@Controller
public class UserController extends ControllerHelper {

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET, value = "/user/settings")
  public Object settings(Model model) {
    User user = getUser();
    model.addAttribute("user", user);
    return "/v1/user/settings";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/user/settings")
  public Object settings(@RequestParam String name, @RequestParam String value) {
    // TODO
    return illegal();
  }


}
