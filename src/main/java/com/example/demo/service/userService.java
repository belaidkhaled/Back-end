package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.userRepo;
import com.example.demo.data.entity.user;

@Service
public class userService {
	
	
	Integer index;
	
	
	@Autowired
	private userRepo repo ;
	
	public List<user> listAll(){
		return repo.findAll();
	}
	
	public user get(int Id) {
		return repo.findById(Id).get();
	}
	
	
	public Integer getIndex() {
		return index;
	}


	public void setIndex(Integer index) {
		this.index = index;
	}

}
