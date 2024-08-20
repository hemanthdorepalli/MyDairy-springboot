package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "data")
public class data {
 
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
    
	@Column(columnDefinition = "TEXT")
	private String entries;
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	private LocalDateTime date; 
	
	   @ManyToOne
	   @JoinColumn(name = "username", referencedColumnName = "username") 
	   private Users user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntries() {
		return entries;
	}

	public void setEntries(String entries) {
		this.entries = entries;
	}

}
