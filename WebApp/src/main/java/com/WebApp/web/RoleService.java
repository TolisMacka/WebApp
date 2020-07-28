package com.WebApp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WebApp.domain.Role;
import com.WebApp.domain.User;
import com.WebApp.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;

	public Role findById(long id) {
		return roleRepo.findById(id);
	}

	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}

	public void save(Role role) {
		roleRepo.save(role);
	}

	public void delete(long id) {
		roleRepo.deleteById(id);
	}

	public void delete(Role role) {
		roleRepo.delete(role);
	}

	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	public List<User> getUsersByRoleName(String nameRole) {
		Role role = roleRepo.findByName(nameRole);
		return role.getUsers();
	}
}
