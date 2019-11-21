package com.nxtlife.spring.boot.hibernate.login.service;

import java.util.List;
import com.nxtlife.spring.boot.hibernate.login.MyException;
import com.nxtlife.spring.boot.hibernate.login.dto.LoginDto;
import com.nxtlife.spring.boot.hibernate.login.dto.SignUpDto;
import com.nxtlife.spring.boot.hibernate.login.entity.User;

public interface UserService {

	public void signUp(SignUpDto signUpDto);

	public User login(LoginDto loginDto) throws MyException;

	public List<User> findAllUser();

}
