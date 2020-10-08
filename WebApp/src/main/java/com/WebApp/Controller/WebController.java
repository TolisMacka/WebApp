package com.WebApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebController {
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String serveLogError(Model model) {
		return "logerror";
	}

	@RequestMapping(value= {"home","index","/"})
	public String index() {
		return "index";
	}
}