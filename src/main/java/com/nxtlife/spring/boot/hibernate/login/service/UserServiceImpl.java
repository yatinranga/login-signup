package com.nxtlife.spring.boot.hibernate.login.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.nxtlife.spring.boot.hibernate.login.MyException;
import com.nxtlife.spring.boot.hibernate.login.dto.LoginDto;
import com.nxtlife.spring.boot.hibernate.login.dto.SignUpDto;
import com.nxtlife.spring.boot.hibernate.login.entity.User;
import com.nxtlife.spring.boot.hibernate.login.repository.UserDao;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public List<User> findAllUser() {
		return userDao.findAll();
	}

	@Override
	public void signUp(SignUpDto signUpDto) {

		User user = new User();

		// ENCRYPTING PASSWORD BY BCRYPT

		signUpDto.setPassword(bCryptPasswordEncoder.encode(signUpDto.getPassword()));

		BeanUtils.copyProperties(signUpDto, user);
		userDao.save(user);

	}

	@Override
	public User login(LoginDto loginDto) throws MyException {

		User user1 = userDao.findByUserName(loginDto.getUserName());

		if (user1 == null) {
			throw new MyException("User does not exist.");
		}

		/*
		 * if (!loginDto.getPassword().equals(user1.getPassword())) { throw new
		 * MyException("Password mismatch."); }
		 */
		if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), user1.getPassword())) {
			throw new MyException("Password mismatch.");
		}

		return user1;

	}

}
