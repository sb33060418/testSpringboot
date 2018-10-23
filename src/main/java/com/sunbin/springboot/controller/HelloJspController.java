package com.sunbin.springboot.controller;

import com.sunbin.springboot.pojo.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloJspController {

    @Autowired
    private Hello hello;

    @RequestMapping("/helloJsp")
    public String helloJsp(Model model) {
    	System.out.println("helloJsp");
        model.addAttribute("msg",  "Hello jsp," + hello.getContent());
        return "helloJsp";
    }
}
