package com.WebApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.WebApp.UserRepositoryAuthenticationProvider;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logIn").authenticated();

		// URLs that need authentication to access to it

		// URLs user
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{id}").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/{id}").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/{id}/role").hasRole("ADMIN");

		// URLs role
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/role/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/role/{id}").hasRole("ADMIN");

		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {
		});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userRepoAuthProvider);
	}
}