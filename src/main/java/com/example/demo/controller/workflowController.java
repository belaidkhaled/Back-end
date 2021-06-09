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

import com.example.demo.data.dao.workflowDocRepo;
import com.example.demo.data.entity.Folder;
import com.example.demo.data.entity.document;
import com.example.demo.data.entity.documentHistory;
import com.example.demo.data.entity.trackArray;
import com.example.demo.data.entity.workflowDoc;
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
	private workflowDocRepo repo;
	
	// CRUD workflow
	
	@RequestMapping(method=RequestMethod.GET,value="/allWorkflow"
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
	


	@RequestMapping(method= {RequestMethod.POST} ,value="/workflows",
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {"application/json","application/pdf"})
	public ResponseEntity<Object> createWorkflow(@RequestPart("document") String doc,
			        @RequestPart("file") MultipartFile  file)  {
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
		try {
			workflowDoc documentJson=service.getJson(doc,file);
			response=service.save(documentJson);
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
	
	
	@RequestMapping(method=RequestMethod.GET,value="/workflowTrack/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getWorkflowTrackById(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
		try {
			response=service.get(Id);
			if(response.trackValidation ==null) {
				response.trackValidation=0;
				response.trackValidation=response.trackValidation + 1;
			}
			else {
				response.trackValidation=response.trackValidation + 1;
			}
			service.save(response);
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

	@RequestMapping(method=RequestMethod.PUT,value="/workflowarray/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getWorkflowArrayById(@PathVariable Integer Id,
			@RequestBody trackArray trk) {
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
		try {
			response=service.get(Id);
			if(response.trackArray ==null) {
				int[] myIntArray = new int[7];
				myIntArray[trk.getIndex()-1]=1;
				response.trackArray= new int[7];
				response.trackArray[trk.getIndex()]=1;
				
			}
			else {
				response.trackArray[trk.getIndex()]=1;
			}
			service.save(response);
		   } 
		catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
		} 
		catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response.trackArray,statusCode);
	}

	@RequestMapping(method=RequestMethod.GET,value="/workflotrackwarray/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getTrackArrayById(@PathVariable Integer Id
			) {
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
		try {
			response=service.get(Id);
			if(response.trackArray==null) {
				int[] myIntArray = new int[7];
				response.trackArray=myIntArray;
			}
			service.save(response);
		   } 
		catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
		} 
		catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response.trackArray,statusCode);
	}
	

	@RequestMapping(method=RequestMethod.PUT,value="/workflowarraytonull/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> putWorkflowArrayToNull(@PathVariable Integer Id,
			@RequestBody trackArray trk) {
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
		try {
			response=service.get(Id);
			response.trackArray[trk.getIndex()]=0;
			
			service.save(response);
		   } 
		catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
		} 
		catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response.trackArray,statusCode);
	}
	

	@RequestMapping(method= {RequestMethod.PUT} ,value="/updateworkflows/{Id}",
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {"application/json","application/pdf"})
	public ResponseEntity<Object> updateWorkflow(@PathVariable Integer Id,
			@RequestPart("document") String doc,
			        @RequestPart(value="file",required = false)  MultipartFile  file)  {
		HttpStatus statusCode = HttpStatus.OK;
		workflowDoc response=null;
		workflowDoc resp=service.get(Id);
		try {
			 repo.findById(Id)
	           .map(workflowDoc -> {
	        	workflowDoc docJson=new workflowDoc();
	       		ObjectMapper objectMapper=new ObjectMapper();
	       		try {
					docJson= objectMapper.readValue(doc,workflowDoc.class);
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           if(docJson.getCategory() != null) 
	           {
	        	 workflowDoc.setCategory(docJson.getCategory());
	           }
	           if(docJson.getHierarchy() != null)
	           {
	        	   workflowDoc.setHierarchy(docJson.getHierarchy());
	           }
	           if(docJson.getWorkflowMembers() != null) {
	        	   workflowDoc.setWorkflowMembers(docJson.getHierarchy());
	           }
	           if(docJson.getComment() !=null) {
	        	   workflowDoc.setComment(docJson.getComment());
	           }
	           if(docJson.getTrackArray() != null) {
	        	   workflowDoc.setTrackArray(workflowDoc.getTrackArray());
	           }
	           if (file.isEmpty()) {
	        	   workflowDoc.setData(resp.getData());
	        	   workflowDoc.setCreationDate(resp.getCreationDate());
	        	   workflowDoc.setFormat(resp.getFormat());
	        	   workflowDoc.setSize(resp.getSize());
	        	   return repo.save(workflowDoc);
		           
	           }
	           workflowDoc d= service.Filldata(workflowDoc, file);
	           repo.save(d);  
	           return repo.save(d);
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
	   public ResponseEntity<Object>deleteWorkflow(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		boolean response = false;
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
		return new ResponseEntity<>(response,statusCode);
	  }
	

		
}
