package com.sunbin.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@RequestMapping("/session")
	public String session(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			session.setAttribute("user", "zhangsan");
			System.out.println("不存在session");
		} else {
			System.out.println("存在session");
		}
		return "session";
	}

}
