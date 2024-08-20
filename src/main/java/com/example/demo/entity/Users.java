package com.example.demo.entity;


import java.util.ArrayList;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name="Users")
public class Users {
	
	private String name;
	
	@Id
	private String username;
	
	private String email;
	
	private String phone;
	
	private String password;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	 @JoinColumn(name = "username", referencedColumnName = "username")
	private List<data> dataa = new ArrayList<>();
	

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


	public List<data> getDataa() {
		return dataa;
	}


	public void setDataa(List<data> dataa) {
		this.dataa = dataa;
	}


}
