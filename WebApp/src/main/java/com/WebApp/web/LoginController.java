package com.WebApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.WebApp.domain.User;
import com.WebApp.services.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register (ModelMap model) {
		model.put("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost (User user) {
		userService.save(user);
		
		return "redirect:/login";
	}
}
