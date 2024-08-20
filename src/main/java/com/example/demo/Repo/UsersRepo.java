package com.example.demo.Repo;

//import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;


public interface UsersRepo extends JpaRepository< Users , String>{

	public Users  findByUsername(String username);
}

