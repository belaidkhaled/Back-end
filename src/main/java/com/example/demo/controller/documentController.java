package com.example.demo.controller;


import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.dao.documentRepo;
import com.example.demo.model.document;
import com.example.demo.service.documentService;


@RestController
public class documentController  {

	public static String  UploadDirectory=System.getProperty("user.dir")+"/uploads";
	
	@Autowired
	private documentService service;
	
	@Autowired
	private documentRepo repo;
	
	
	@GetMapping("/alldocuments")
	public List<document> getAllDocuments() {
		 return service.listAll() ;

	}
	
	@GetMapping("/documents/{Id}")
	public document get(@PathVariable Integer Id) {
		return service.get(Id);
	}
	
	@PostMapping(value="/documents",consumes= {MediaType.APPLICATION_JSON_VALUE,
										 MediaType.MULTIPART_FORM_DATA_VALUE})
	public document add(@RequestPart("document") String doc,
			        @RequestPart("file") MultipartFile  file) throws IOException {
		document documentJson=service.getJson(doc,file);
		
		service.save(documentJson);
		return documentJson;
		
	}
	
	@RequestMapping(value="/documents/{id}",
			produces = "application/json",
			method = { RequestMethod.PUT}
			  )
	public document updateDoc(@RequestBody document doc,
			@PathVariable Integer Id){
		     repo.findById(Id)
		           .map(document -> {
		           document.setTitle(doc.getTitle());
		           document.setDescription(doc.getDescription());
		           document.setSubject(doc.getSubject());
		           document.setName(doc.getName());
		           
		           return repo.save(document);
		           });
			return doc;
	}
	
	@RequestMapping(value="/documents/{Id}",
			produces = "application/json",
			method = { RequestMethod.DELETE}
			  )
	   public void deleteDocument(@PathVariable Integer Id) {
	    service.delete(Id);
	  }
	
	
}
