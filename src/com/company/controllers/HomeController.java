package com.company.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHomePage(Principal principal, HttpSession session) {
		if (principal != null) {
			String username = principal.getName();
			session.setAttribute("username", username);
		}
		return "home";
	}

}
