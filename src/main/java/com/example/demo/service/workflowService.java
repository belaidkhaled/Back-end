package com.example.demo.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.data.dao.workflowDocRepo;
import com.example.demo.data.entity.document;
import com.example.demo.data.entity.workflowDoc;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class workflowService {
	@Autowired
	private workflowDocRepo repo ;
	
	public List<workflowDoc> listAll(){
		return repo.findAll();
	}
	
	public workflowDoc save(workflowDoc workflow) {
		repo.save(workflow);
		return workflow;
	}
	
	public workflowDoc get(int Id) {
		return repo.findById(Id).get();
	}
	
	public void delete(int Id) {
		 repo.deleteById(Id);
		   
	}
	

	public workflowDoc getJson(String doc) throws IOException {
       
			workflowDoc docJson=new workflowDoc();
		
			try {
				ObjectMapper objectMapper=new ObjectMapper();
				docJson= objectMapper.readValue(doc,workflowDoc.class);
			}
			catch(IOException err){
				System.out.printf("error",err.toString());
			}
			
			
			repo.save(docJson);  
			return docJson;    
    }
	
	
	 
	 
		
}
