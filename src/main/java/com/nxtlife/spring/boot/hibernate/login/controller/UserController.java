package com.nxtlife.spring.boot.hibernate.login.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nxtlife.spring.boot.hibernate.login.MyException;
import com.nxtlife.spring.boot.hibernate.login.dto.LoginDto;
import com.nxtlife.spring.boot.hibernate.login.dto.SignUpDto;
import com.nxtlife.spring.boot.hibernate.login.entity.User;
import com.nxtlife.spring.boot.hibernate.login.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> listAllUser() {
		List<User> user = userService.findAllUser();

		if (user.isEmpty()) {

			return new ResponseEntity<Object>("NO USER FOUND", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {

		userService.signUp(signUpDto);
		return new ResponseEntity<Object>("SIGNUP SUCCESSFULL", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		User user = null;
		try {
			user = userService.login(loginDto);
		} catch (MyException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
