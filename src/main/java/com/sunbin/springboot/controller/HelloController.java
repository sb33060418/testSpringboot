package com.sunbin.springboot.controller;

import com.sunbin.springboot.pojo.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${hello.name}")
    private String name;

    @Value("${hello.age}")
    private int age;

    @Value("${hello.content}")
    private String content;

    @Autowired
    private Hello hello;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @RequestMapping("/helloProperties")
    public String helloProperties() {
        return "Hello," + name + ":" + age + ".content=" + content;
    }

    @RequestMapping("/helloPropertiesPojo")
    public Hello helloPropertiesPojo() {
        return hello;
    }
}
