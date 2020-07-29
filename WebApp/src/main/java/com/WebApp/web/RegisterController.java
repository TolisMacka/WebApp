package com.WebApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.WebApp.UserSession;
import com.WebApp.domain.User;
import com.WebApp.services.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;
	@Autowired
	public UserSession userSession;

	@GetMapping(value = { "register" })
	public String serveRegister(Model model) {
		return "register";
	}

	@RequestMapping(value = { "/registerUser" }, method = RequestMethod.POST)
	public String registerUser(Model model, RegisterDataForm user) {

		User newUser = new User(user.getEmail(), user.getName(), user.getFirstName(), user.getLastName(),
				user.getPasswordHash(), "ROLE_USER");

		userService.save(newUser);

		userSession.setIsLogggedIn(true);
		userSession.setUsername(newUser.getName());

		return "/";
	}

}