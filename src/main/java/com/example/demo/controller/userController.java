package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.dao.userRepo;
import com.example.demo.data.entity.Folder;
import com.example.demo.data.entity.user;
import com.example.demo.service.userService;

@CrossOrigin
@Controller
@RestController
public class userController {
	
	


	@Autowired
	private userService service;
	
	@Autowired
	private userRepo repo;
	
	@RequestMapping(method=RequestMethod.GET,value="/allUsers"
			,produces = "application/json")
	public ResponseEntity<Object> getAllUsers() {
		HttpStatus statusCode = HttpStatus.OK;
		List<user> response=null;
		try {
		  response = service.listAll();
		} catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response,statusCode);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/CurrentUser"
			,produces="application/json")
	public ResponseEntity<Object> getCurrentUser() {
		HttpStatus statusCode = HttpStatus.OK;
		List<user> response=null;
		try {
			 response = service.listAll();
		   } 
		catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
		} 
		catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response.get(service.getIndex()),statusCode);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/allUsersName"
			,produces = "application/json")
	public ResponseEntity<Object> getAllUsersName() {
		HttpStatus statusCode = HttpStatus.OK;
		List<user> response=null;
		String tr;
		String tr1;
		String tr2;
		List<List<String>> myList = new ArrayList<>();
		List<String> myList0 = new ArrayList<>();
		List<String> myList1 = new ArrayList<>();
		try {
		  response = service.listAll();
		  for(int i=0;i<response.size();i++) {
			  tr=response.get(i).UTCTNOM;
			  tr1=tr.replaceAll("\\s", "");
			  tr2=response.get(i).UTCTSUPER;
			  myList0.add(tr1);  
			  myList1.add(tr2);
		  }
		  myList.add(myList0);
		  myList.add(myList1);
		} catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(myList,statusCode);
	}
	
		
	

}
