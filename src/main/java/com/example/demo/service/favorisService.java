package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.favorisRepo;
import com.example.demo.data.entity.Favoris;

@Service
public class favorisService {
	
	@Autowired
	private favorisRepo repo ;
	
	public List<Favoris> listAll(){
		return repo.findAll();
	}
	
	public Favoris save(Favoris favoris) {
		repo.save(favoris);
		return favoris;
	}
	
	public Favoris get(int Id) {
		return repo.findById(Id).get();
	}
	
	public void delete(int Id) {
	   repo.deleteById(Id);
	   
	}
	
}
