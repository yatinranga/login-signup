package com.nxtlife.spring.boot.hibernate.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nxtlife.spring.boot.hibernate.login.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	public User findByUserName(String username);

	// public User findByEmail(String email);

}
