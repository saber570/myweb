package com.pengwei.zhou.app.controller;

import com.pengwei.zhou.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController{

  @Autowired
  TestService testService;

  @RequestMapping("/index")
  public String index(ModelMap map){
    map.addAttribute("host","www.baidu.com");
    return "index";
  }

  @RequestMapping("/freemarker")
  public String freemarker(){
    return testService.handle("");
  }
}