package com.WebApp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.WebApp.domain.Role;
import com.WebApp.domain.User;
import com.WebApp.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	// get all existing users
	@JsonView(User.Basic.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<User>> getUsers(HttpSession session) {
		// admin can see all users
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	// get an existing user
	@JsonView(User.Basic.class)
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id, Authentication auth, HttpServletRequest request,
			HttpSession session) {

		User user = userService.findById(id);

		if (user != null) {
			if ((auth.getName().equals(user.getName())) || (request.isUserInRole("ADMIN"))) {
				// see the user login
				// admin can see any user
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// add a new user, permitted all
	@JsonView(User.Basic.class)
	@PostMapping("/")
	public ResponseEntity<User> postUser(@RequestBody User user) {

		boolean result = userService.createAnUser(user);
		if ((result == true) && (user.getName() != null)) {
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	// delete an existing user
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable long id, Authentication auth, HttpServletRequest request,
			HttpSession session) {

		User userFound = userService.findById(id);
		if (userFound != null) {
			if (auth.getName().equals(userFound.getName()) || (request.isUserInRole("ADMIN"))) {
				// delete the user login
				// admin can delete any user
				userService.deleteByUser(userFound);
				return new ResponseEntity<>(userFound, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * @DeleteMapping("/{id}") public ResponseEntity<Object>
	 * deleteUserById(@PathVariable long id) { User userFound =
	 * userService.findById(id);
	 * 
	 * if(userFound==null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * userService.deleteById(id);
	 * 
	 * return new ResponseEntity<>(true, HttpStatus.OK); }
	 */

	// update an existing user
	@JsonView(User.Basic.class)
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userUpdate, Authentication auth,
			HttpServletRequest request, HttpSession session) {

		User userFound = userService.findById(id);
		if (userFound != null) {
			if (auth.getName().equals(userFound.getName()) || (request.isUserInRole("ADMIN"))) {
				// update the user login
				// admin can update any user
				if (userUpdate.getName() != null) {
					userFound.setName(userUpdate.getName());
				}

				if (userUpdate.getFirstName() != null) {
					userFound.setFirstName(userUpdate.getFirstName());
				}

				if (userUpdate.getLastName() != null) {
					userFound.setLastName(userUpdate.getLastName());
				}

				if (userUpdate.getEmail() != null) {
					userFound.setEmail(userUpdate.getEmail());
				}

				if (userUpdate.getRoles() != null) {
					userFound.setRoles(userUpdate.getRoles());
				}
				// needed to put all role
				if (userUpdate.getRole() != null) {
					userFound.setRole(userUpdate.getRole());
				}
				userService.save(userFound);
				return new ResponseEntity<>(userFound, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(User.Basic.class)
	@PutMapping("/{id}/role")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> editUserRole(@PathVariable long id, @RequestBody String newRoleId) {

		Long roleId = Long.parseLong(newRoleId.replaceAll("\"", ""));

		Role roleObject = roleService.findById(roleId);

		User user = userService.findById(id);

		if (user != null) {

			user.setRole(roleObject);

			userService.save(user);

			return new ResponseEntity<>(user, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
