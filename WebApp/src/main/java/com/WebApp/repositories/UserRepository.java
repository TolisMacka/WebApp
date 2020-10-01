package com.WebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebApp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

	User findByEmail(String email);

	User findById(long id);
}