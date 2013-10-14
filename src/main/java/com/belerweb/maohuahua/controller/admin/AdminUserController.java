package com.belerweb.maohuahua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.belerweb.maohuahua.controller.ControllerHelper;
import com.belerweb.maohuahua.service.UserService;

@Controller
public class AdminUserController extends ControllerHelper {

  @Autowired
  private UserService userService;

  @RequestMapping("/admin/user/list")
  public Object list(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", userService.getUsers(page, pageSize));
    return "v1/admin/user/list";
  }

}
