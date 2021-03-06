package com.WebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebApp.back.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

	Role findById(long id);

}
