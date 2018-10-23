package com.sunbin.springboot.controller;

import com.sunbin.springboot.mapper.UserMapper;
import com.sunbin.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user")
    public String user(Model model) {
        List<User> users = userMapper.findAll();
        model.addAttribute("users",  users);
        return "user";
    }
}
