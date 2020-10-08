package com.WebApp.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.WebApp.back.User;

@Component
@SessionScope
public class UserComponent {

	private User user;

	public User getLoggedUser() {
		return user;
	}

	public void setLoggedUser(User user) {
		this.user = user;
	}

	public boolean isLoggedUser() {
		return this.user != null;
	}

}
