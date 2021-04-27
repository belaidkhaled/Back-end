package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.data.dao.documentRepo;
import com.example.demo.data.dao.folderRepo;
import com.example.demo.data.entity.Folder;
import com.example.demo.data.model.document;
import com.example.demo.service.documentService;
import com.example.demo.service.folderService;

@CrossOrigin
@Controller
@RestController
public class folderController {
	@Autowired
	private folderService service;
	
	@Autowired
	private folderRepo repo;
	
	
	// CRUD documents
	
	@RequestMapping(method=RequestMethod.GET,value="/allfolders"
			,produces = "application/json")
	public ResponseEntity<Object> getAllFolders() {
		HttpStatus statusCode = HttpStatus.OK;
		List<Folder> response=null;
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
		
	
	
	@RequestMapping(method=RequestMethod.GET,value="/folders/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getFolderById(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		Folder response=null;
		try {
			response=service.get(Id);
		   } 
		catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
		} 
		catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response,statusCode);
	}
	
	
	@RequestMapping(method= {RequestMethod.POST} ,value="/folders",
			consumes= MediaType.APPLICATION_JSON_VALUE,
			produces = "application/json")
	public ResponseEntity<Object> createFolder(@RequestBody Folder folder)  {
		HttpStatus statusCode = HttpStatus.OK;
		Folder response=null;
		try {
			response=service.save(folder);
		}
		catch(EntityNotFoundException e){
			statusCode=HttpStatus.GONE;
		}
		catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response,statusCode);
	}
	
	
	@RequestMapping(method = {RequestMethod.PUT},value="/folders/{Id}",
			produces = "application/json")
	public ResponseEntity<Object> updateFolder(@PathVariable Integer Id,
			@RequestBody Folder folder)
	{
		HttpStatus statusCode = HttpStatus.OK;
		Folder response=null;
			 try {
		     repo.findById(Id)
		           .map(Folder -> {
		           Folder.setDescription(folder.getDescription());
		           Folder.setMaxSize(folder.getMaxSize());
		           Folder.setName(folder.getName());
		           return repo.save(Folder);
		           });
			}
			 catch(EntityNotFoundException e) {
				 statusCode=HttpStatus.GONE;
			 }
			 catch(Exception e) {
				 e.printStackTrace();
				 statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
			 }
			 return new ResponseEntity<>(response,statusCode);
	}
	
	
	@RequestMapping(value="/folders/{Id}",produces = "application/json",
			method =  RequestMethod.DELETE)
	   public ResponseEntity<Object>deleteFolder(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		boolean response = false;
		try {
			response= service.delete(Id);;
		}
		catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		}
		catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response,statusCode);
	    
	  }
	
	
	@RequestMapping(method=RequestMethod.GET,value="/fodlersParentsId/{Id}"
			,produces = "application/json")
	public ResponseEntity<Object> getFolderParentId(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		List<Folder> response=null;
		List<Folder> newresp [] ;
		List<Folder> myList = new ArrayList<>();
		try {
		  response=service.listAll();
		  for(int i=0;i<response.size();i++) {
			   Folder dc=response.get(i);
			   if (dc.getParentFolderId()== Id) {
				   myList.add(dc);
			   }
		  }
		} catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(myList,statusCode);
	}
	
}
