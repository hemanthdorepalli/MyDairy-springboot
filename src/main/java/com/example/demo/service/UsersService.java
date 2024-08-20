package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repo.DataRepo;
import com.example.demo.Repo.UsersRepo;
import com.example.demo.entity.Users;
import com.example.demo.entity.data;

@Service
public class UsersService implements UserDetailsService{
	
	@Autowired
	private UsersRepo usersRepo;
	
	@Autowired
    private DataRepo dataRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Users users = usersRepo.findByUsername(username);
	    
	    if (users == null) {
	        throw new UsernameNotFoundException("User not found with username: " + username);
	    }
	    
	    return User.withUsername(users.getUsername())
	               .password(users.getPassword())
	               .roles(users.getName())
	               .build();
	}

	
	

    @Transactional
    public void addData(String entries, String username) {
        // Find the user by username
        Users user = usersRepo.findByUsername(username);

        if (user != null) {
        	
            // Create a new Data entry
            data data = new data();
            data.setEntries(entries);
            data.setDate(LocalDateTime.now());
            data.setUser(user);

            // Save the Data entry
            dataRepo.save(data);
        } else {
            throw new RuntimeException("User not found");
        }
    }
    
    @Transactional
    public void deleteEntry(int id) {
        data entry = dataRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid entry Id:" + id));
        dataRepo.delete(entry);
    }
    

}
