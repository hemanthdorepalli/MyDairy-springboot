package com.example.demo.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterDto {
	
	@NotEmpty
	private String name;
	
	@Id
	@Column(unique = true, nullable = true)
	private String username;
	
	@NotEmpty
	@Email(message ="Invalid Email")
	private String email;
	

	@NotEmpty
	@Size(max=10)
	private String phone;
	
	
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$" , message="password must contain alpha-numeric characters")
	private String password;
	
	
	private String cpassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	
	

}
