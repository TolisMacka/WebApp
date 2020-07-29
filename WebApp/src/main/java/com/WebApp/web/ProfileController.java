package com.WebApp.web;

import javax.servlet.http.HttpServletRequest;

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
public class ProfileController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserSession session;

	@GetMapping("/profile")
	public String profileModel(Model model, HttpServletRequest request) {
		model.addAttribute("session", session);
		return "profile";
	}

	@RequestMapping(value = { "/profile" }, method = RequestMethod.POST)
	public String editUser(Model model, RegisterDataForm user) {

		User edit = userService.findByName(session.getUsername());

		edit.setEmail(user.getEmail());
		edit.setFirstName(user.getFirstName());
		edit.setLastName(user.getLastName());

		session.setEmail(user.getEmail());
		session.setFirstName(user.getFirstName());
		session.setLastName(user.getLastName());

		userService.save(edit);
		return "profile";
	}

}
