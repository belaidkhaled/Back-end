package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.documentHistoryRepo;
import com.example.demo.data.entity.document;
import com.example.demo.data.entity.documentHistory;

@Service
public class documentHistoryService {
	
	@Autowired
	private documentHistoryRepo repo ;
	
	
	public List<documentHistory> listAll(){
		return repo.findAll();
	}
	
	public documentHistory save(documentHistory doc) {
		repo.save(doc);
		return doc;
	}
	
	public documentHistory get(int Id) {
		return repo.findById(Id).get();
	}
	
	public boolean delete(int Id) {
	   repo.deleteById(Id);
	   document doc=repo.findById(Id).get();
	   if(doc == null) {
		   return true;
	   }
	   else return false;
	}
	
	public String getTheName(int Id) {
		documentHistory doc;
		doc=repo.findById(Id).get();
		return doc.getName();
	}
	
	public boolean exist(int Id) {
		documentHistory doc;
		doc= repo.findById(Id).get();
		boolean ex=repo.findById(Id).isEmpty();
		if(doc.getName() == null) {
			return false;
		}
		else return true ;
	}
	
}
