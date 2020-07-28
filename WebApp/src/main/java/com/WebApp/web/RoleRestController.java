package com.WebApp.web;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebApp.domain.Role;
import com.WebApp.domain.User;
import com.WebApp.services.RoleService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {

	@Autowired
	private RoleService roleService;

	// permited to all
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Role>> getAllRole(HttpSession session) {

		List<Role> allRole = roleService.findAll();
		return new ResponseEntity<>(allRole, HttpStatus.OK);
	}

	// only permited to admin
	@JsonView(User.Basic.class)
	@GetMapping("/{id}")
	public ResponseEntity<List<User>> getAllUsersByRole(@PathVariable long id, HttpSession session) {

		Role roleSearch = roleService.findById(id);

		if (roleSearch != null) {
			List<User> allUsersByRole = roleService.getUsersByRoleName(roleSearch.getName());
			return new ResponseEntity<>(allUsersByRole, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
