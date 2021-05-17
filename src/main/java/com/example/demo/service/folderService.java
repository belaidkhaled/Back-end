package com.example.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.folderRepo;
import com.example.demo.data.entity.Folder;


@Service
public class folderService {

	@Autowired
	private folderRepo repo ;
	
	public List<Folder> listAll(){
		return repo.findAll();
	}
	
	public Folder save(Folder folder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		folder.setCreationDate(dateFormat.format(cal.getTime()));
		repo.save(folder);
		return folder;
	}
	
	public Folder get(int Id) {
		return repo.findById(Id).get();
	}
	
	public void delete(int Id) {
		 repo.deleteById(Id);
		   
	}
}
