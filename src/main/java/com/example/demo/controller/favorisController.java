package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.dao.favorisRepo;
import com.example.demo.data.entity.Favoris;
import com.example.demo.data.entity.Folder;
import com.example.demo.data.entity.document;
import com.example.demo.data.entity.documentValidation;
import com.example.demo.service.documentService;
import com.example.demo.service.favorisService;


@CrossOrigin
@Controller
@RestController
public class favorisController {
	@Autowired
	private documentService servicedoc;
	
	@Autowired
	private favorisService service;
	
	@Autowired
	private favorisRepo repo;
	
	// CRUD favoris
	
		@RequestMapping(method=RequestMethod.GET,value="/favoris"
				,produces = "application/json")
		public ResponseEntity<Object> getAllFavoris() {
			HttpStatus statusCode = HttpStatus.OK;
			List<Favoris> response=null;
			try {
			  response= service.listAll();
			} catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
			} catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity<>(response,statusCode);
		}
			
		@RequestMapping(method=RequestMethod.GET,value="/favoris/download/{Id}"
				,produces="application/json")
		public ResponseEntity<Resource> downloadDocumentFromFavoris(@PathVariable Integer Id) {
			HttpStatus statusCode = HttpStatus.OK;
			Favoris resp=null;
			try {
				resp=service.get(Id);
				String fileName=resp.getName();
			   } 
			catch(EntityNotFoundException e) {
					statusCode=HttpStatus.GONE;
			} 
			catch(Exception e) {
					e.printStackTrace();
					statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
			}
	                                           
	        byte[] bytes = resp.getData();            
	        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Disposition", String.format("attachment; filename=your_file_name"));    
	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(bytes.length)
	                .contentType(org.springframework.http.MediaType.parseMediaType(resp.getFormat()))
	                .body(resource);		
		}
		
		@RequestMapping(method=RequestMethod.GET,value="/favoris/{Id}"
				,produces="application/json")
		public ResponseEntity<Object> getFavorisById(@PathVariable Integer Id) {
			HttpStatus statusCode = HttpStatus.OK;
			Favoris resp=null;
			try {
				resp=service.get(Id);
			   } 
			catch(EntityNotFoundException e) {
					statusCode=HttpStatus.GONE;
			} 
			catch(Exception e) {
					e.printStackTrace();
					statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
			}
	                      
			return new ResponseEntity<>(resp,statusCode);  		
		}
		
		
		@RequestMapping(method= {RequestMethod.POST} ,value="/favoris/{Id}")
		public ResponseEntity<Object> addDocumentToFavoris(@PathVariable Integer Id)  {
			HttpStatus statusCode = HttpStatus.OK;
			document response=null;
			Favoris fav=new Favoris() ;
			try {
				
				document doc=servicedoc.get(Id);
				fav.setCreationDate(doc.getCreationDate());
				fav.setData(doc.getData());
				fav.setDescription(doc.getDescription());
				fav.setFormat(doc.getFormat());
				fav.setParentFolderId(doc.getParentFolderId());
				fav.setName(doc.getName());
				fav.setSize(doc.getSize());
				fav.setSubject(doc.getSubject());
				fav.setTitle(doc.getTitle());
				fav.setUserName(doc.getUserName());
				response=service.save(fav);
		
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
		
		@RequestMapping(value="/favoris/{Id}",produces = "application/json",
				method =  RequestMethod.DELETE)
		   public ResponseEntity<Object> deletefavoris(@PathVariable Integer Id) {
			HttpStatus statusCode = HttpStatus.OK;
			document response=null;
			try {
					response=service.get(Id);
					service.delete(Id);
			  
			}
			catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
			}
			catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR ;
			}
			return new ResponseEntity<>(response,statusCode);
		    
		  } 
		
		@RequestMapping(method=RequestMethod.GET,value="/favorisForUser/{value}"
				,produces = "application/json")
		public ResponseEntity<Object> getFavorisForThisUser(@PathVariable String value) {
			HttpStatus statusCode = HttpStatus.OK;
			List<Favoris> response=null;
			List<Favoris> myList = new ArrayList<>();
			String tr=null;
			String tr1=null;
			tr1=value.replaceAll("\\s", "");
			try {
				 response= service.listAll();
				for(int i=0;i<response.size();i++) {
					Favoris dc=response.get(i);
					if(dc.getUserName() != null) {
						tr= dc.getUserName().replaceAll("\\s", "");
						if(tr.equals(tr1)) {
							myList.add(dc);
						}
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
		
		
		//get favorites numbers for this user
				@RequestMapping(value="/favoritesnumber/{user}",produces = "application/json",
						method =  RequestMethod.GET)
				   public ResponseEntity<Object>getAlldocsInworkflow(@PathVariable String user) {
					HttpStatus statusCode = HttpStatus.OK;
					boolean r=false;
					List<Favoris> myList = new ArrayList<>();
					List<Favoris> response=null;
					boolean resp = false;
					String p;
					String k;
					k=user.replaceAll("\\s", "");
					int[] arr=new int[5] ;
					int i1=0;
					try {
						response=service.listAll();
					
						  for(int i=0;i<response.size();i++) {
							   Favoris dc=response.get(i);
							   if (dc.getUserName().replaceAll("\\s", "").equals(k)) {
								   i1++;
							   }
							   
						  }
						  
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
				
				//delete favorites controller
				@RequestMapping(value="/documentsfavorites/{Id}",produces = "application/json",
						method =  RequestMethod.DELETE)
				   public ResponseEntity<Object>deleteDocumentInfavorites(@PathVariable Integer Id) {
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
