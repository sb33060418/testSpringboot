package com.sunbin.springboot.controller;

import com.sunbin.springboot.mapper.UserMapper;
import com.sunbin.springboot.pojo.User;
import com.sunbin.springboot.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserRedisController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userRedis/{id}")
    public String user(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user",  user);
        return "userRedis";
    }
}
