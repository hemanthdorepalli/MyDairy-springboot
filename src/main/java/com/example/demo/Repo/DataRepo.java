package com.example.demo.Repo;

import com.example.demo.entity.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepo extends JpaRepository<data, Integer> {
	List<data> findByUser_Username(String username);
    
}
