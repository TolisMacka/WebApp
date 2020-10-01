package com.WebApp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.WebApp.domain.Role;
import com.WebApp.domain.User;
import com.WebApp.security.UserSession;
import com.WebApp.services.RoleService;
import com.WebApp.services.UserService;

@Controller
public class DashboardController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserSession session;

	int i;

	@GetMapping("/dashboard")
	public String serveDashboard(Model model) {

		model = fillUserTab(model);

		if (!session.getIsLogggedIn()) {
			return "redirect:/login";
		}

		return "dashboard";
	}

	private Model fillUserTab(Model model) {
		List<User> userList;
		List<Role> roleList;

		userList = userService.findAll(Sort.by(Sort.Direction.ASC, "name"));
		roleList = roleService.findAll();

		model.addAttribute("UserList", userList);
		model.addAttribute("RoleList", roleList);

		return model;
	}

}
