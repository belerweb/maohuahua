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
public class SiteController extends ControllerHelper {

  @Autowired
  private UserService userService;

  @RequestMapping("/site/domain")
  public Object domain(Model model) {
    model.addAttribute("user", getUser());
    return "/v1/site/domain";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/site/settings")
  public Object settings(Model model) {
    User user = getUser();
    model.addAttribute("user", user);
    model.addAttribute("site", userService.getUserSite(user.getId()));
    return "/v1/site/settings";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/site/settings")
  public Object settings(@RequestParam String property, @RequestParam String value) {
    User user = getUser();
    if ("name".equals(property) || "title".equals(property) || "description".equals(property)) {
      userService.updateUserSite(user.getId(), property, value);
      return ok();
    }
    return illegal();
  }


}
