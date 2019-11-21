package com.nxtlife.spring.boot.hibernate.login.dto;

public class LoginDto {

	private String userName;
	private String password;

	// GETTER AND SETTER

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
