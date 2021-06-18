package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.io.FileUtils;
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

import com.example.demo.data.dao.folderRepo;
import com.example.demo.data.dao.workflowDocRepo;
import com.example.demo.data.entity.Folder;
import com.example.demo.data.entity.document;
import com.example.demo.data.entity.documentHistory;
import com.example.demo.data.entity.documentValidation;
import com.example.demo.data.entity.trackArray;
import com.example.demo.data.entity.workflowData;
import com.example.demo.data.entity.workflowDoc;
import com.example.demo.service.documentValidationService;
import com.example.demo.service.folderService;
import com.example.demo.service.workflowService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin
@Controller
@RestController
public class workflowController {

	@Autowired
	private workflowService service;
	
	@Autowired
	private documentValidationService serviceDocV;
	
	@Autowired
	private workflowDocRepo repo;
	
	// CRUD documents
	
		@RequestMapping(method=RequestMethod.GET,value="/allworkflow"
				,produces = "application/json")
		public ResponseEntity<Object> getAllWorkflow() {
			HttpStatus statusCode = HttpStatus.OK;
			List<workflowDoc> response=null;
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
	
		
		@RequestMapping(method=RequestMethod.GET,value="/allworkflowsForThisUserForthisCategory/{data}"
				,produces = "application/json")
		public ResponseEntity<Object> getWorkflowForthiUserAndCategory(@PathVariable String data) {
			HttpStatus statusCode = HttpStatus.OK;
			String[] parts = data.split("-");
			String category = parts[0];
			String username = parts[1];
			List<workflowDoc> response=null;
			List<workflowDoc> myList=new ArrayList<>();
			String p;
			String k;
			try {
			  response = service.listAll();
			  
			  for(int i=0;i<response.size();i++) {
				  workflowDoc dc=response.get(i);
				  p=dc.getMainUser();
				  k=dc.getCategory().replaceAll("\\s", "");
				  if(p.equals(username.replaceAll("\\s", "")) && k.equals(category.replaceAll("\\s", "")) ) {
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
		
		
		
		@RequestMapping(method=RequestMethod.GET,value="/allworkflowsForThisUser/{data}"
				,produces = "application/json")
		public ResponseEntity<Object> getWorkflowForthiUserAllCategory(@PathVariable String data) {
			HttpStatus statusCode = HttpStatus.OK;
			List<documentValidation> response=null;
			List<documentValidation> myList=new ArrayList<>();
			String p;
			try {
			  response = serviceDocV.listAll();
			  
			  for(int i=0;i<response.size();i++) {
				  documentValidation dc=response.get(i);
				  p=dc.getUserName().replaceAll("\\s", "");
				  if(p.equals(data.replaceAll("\\s", "")) ) {
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
	
	
	
	@RequestMapping(method= {RequestMethod.POST} ,value="/workflow",
			consumes= MediaType.APPLICATION_JSON_VALUE,
			produces = "application/json")
	public ResponseEntity<Object> createFolder(@RequestBody workflowDoc workflowDc)  {
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			workflowDc.setCreated_At(dateFormat.format(cal.getTime()));
			response=service.save(workflowDc);
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
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/workflow/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getWorkflowById(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
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
	
	
	@RequestMapping(method = {RequestMethod.PUT},value="/workflow/{Id}",
			produces = "application/json")
	public ResponseEntity<Object> updateFolder(@PathVariable Integer Id,
			@RequestBody workflowDoc workflowdoc)
	{
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
			 try {
		     repo.findById(Id)
		           .map(workflowDoc -> {
		        	   workflowDoc.setCategory(workflowDoc.getCategory());
		        	   workflowDoc.setCommentSupervisor(workflowDoc.getCommentSupervisor());
		        	   workflowDoc.setHierarchy(workflowDoc.getHierarchy());
		        	   workflowDoc.setLevel1(workflowDoc.getLevel1());
		        	   workflowDoc.setLevel2(workflowDoc.getLevel2());
		        	   workflowDoc.setLevel3(workflowDoc.getLevel3());
		        	   workflowDoc.setLevel4(workflowDoc.getLevel4());
		        	   workflowDoc.setLevel5(workflowDoc.getLevel5());
		        	   workflowDoc.setMainUser(workflowDoc.getMainUser());
		           return repo.save(workflowDoc);
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
	
	
	@RequestMapping(value="/workflow/{Id}",produces = "application/json",
			method =  RequestMethod.DELETE)
	   public ResponseEntity<Object>deleteworkflow(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		int i1=0;
		try {
			service.delete(Id);
			   
		}
		catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		}
		catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(i1,statusCode);
	    
	  }
	
	
	

		
}
