package com.example.demo.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.data.dao.documentHistoryRepo;
import com.example.demo.data.dao.documentRepo;
import com.example.demo.data.entity.Favoris;
import com.example.demo.data.entity.Folder;
import com.example.demo.data.entity.document;
import com.example.demo.data.entity.documentHistory;
import com.example.demo.data.entity.documentValidation;
import com.example.demo.service.documentHistoryService;
import com.example.demo.service.documentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@Controller
@RestController
public class documentController  {

	@Autowired
	private documentHistoryService historyService;
	
	@Autowired
	private documentService service;
	
	@Autowired
	private documentRepo repo;
	
	@Autowired
	private documentHistoryRepo dhrepo;
	
	
	// CRUD documents
	
	@RequestMapping(method=RequestMethod.GET,value="/alldocuments"
			,produces = "application/json")
	public ResponseEntity<Object> getAllDocuments() {
		HttpStatus statusCode = HttpStatus.OK;
		List<document> response=null;
		try {
		  response=service.listAll();
		} catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response,statusCode);
	}
		
	@RequestMapping(method=RequestMethod.GET,value="/documents/download/{Id}"
			,produces="application/json")
	public ResponseEntity<Resource> downloadDocument(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		document resp=null;
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
        String form=resp.getFormat() ;
        String path=resp.getPath();
        String val="pdf";
        String format=null;
        String val1="xlsx";
        String val2="docx";
        String val3="pptx";
        String val4="txt";
        if(path.contains(val)) {
        	format="pdf";
        }
        if(path.contains(val1)) {
        	format="xlsx";
        }
        if(path.contains(val2)) {
        	format="docx";
        }
        if(path.contains(val3)) {
        	format="pptx";
        }
        if(path.contains(val4)) {
        	format="txt";
        }
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", String.format("attachment; filename=your_file_name"+"."+ format));    
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(bytes.length)
                .contentType(org.springframework.http.MediaType.parseMediaType(resp.getFormat()))
                .body(resource);		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/documents/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getDocumentById(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		document resp=null;
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
	
	
	@RequestMapping(method= {RequestMethod.POST} ,value="/documents",
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {"application/json","application/pdf"})
	public ResponseEntity<Object> createDocument(@RequestPart("document") String doc,
			        @RequestPart("file") MultipartFile  file)  {
		HttpStatus statusCode = HttpStatus.OK;
		document response=null;
		try {
			document documentJson=service.getJson(doc,file);
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
	
	
	
	@RequestMapping(method = {RequestMethod.PUT},value="/documents/{Id}",
			produces = "application/json",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> updateDocument(@PathVariable Integer Id,
			@RequestPart("document") String doc,
	        @RequestPart(name= "file" ,required = false)  MultipartFile  file ) throws JsonMappingException, JsonProcessingException
	{
		HttpStatus statusCode = HttpStatus.OK;
		int k=0;
		document resp;
		documentHistory dhc = null;
		resp=service.get(Id);
		List<documentHistory> response=null;
		List<documentHistory> myList=new ArrayList<>();
		int p=0;
		boolean tr = false;
	    try {
		response=historyService.listAll();
		
		for(int i=0;i<response.size();i++) {
			documentHistory dc=response.get(i);
			p=dc.getOriginalId();
				if (dc.getOriginalId()== Id){
					myList.add(dc);
							}
					}
			for(int j=0; j<myList.size();j++) {
				if ((myList.get(j).getVersion()) > k) {
				k=myList.get(j).getVersion();
					}
				}
			if(file != null) {
				
			
			if( k==0) {
						documentHistory dh=new documentHistory();
						dh.setData(resp.getData());
						dh.setCreationDate(resp.getCreationDate());
						dh.setDescription(resp.getDescription());
						dh.setFormat(resp.getFormat());
						dh.setPath(resp.getPath());
						dh.setVersion(1);
						dh.setName(resp.getName());
						dh.setSize(resp.getSize());
						dh.setTitle(resp.getTitle());
						dh.setSubject(resp.getSubject());
						dh.setParentFolderId(resp.getParentFolderId());
						dh.setOriginalId(Id);
						dh.setUserName(resp.getUserName());
						dhrepo.save(dh); 
					}
					else {
						documentHistory dh=new documentHistory();
						dh.setData(resp.getData());
						dh.setName(resp.getName());
						dh.setCreationDate(resp.getCreationDate());
						dh.setDescription(resp.getDescription());
						dh.setFormat(resp.getFormat());
						dh.setPath(resp.getPath());
						dh.setVersion(k+1);
						dh.setOriginalId(Id);
						dh.setParentFolderId(resp.getParentFolderId());
						dh.setSize(resp.getSize());
						dh.setTitle(resp.getTitle());
						dh.setSubject(resp.getSubject());
						dh.setUserName(resp.getUserName());
						dhrepo.save(dh); 
					}
			}
		     repo.findById(Id)
		           .map(document -> {
		        	document docJson=new document();
		       		ObjectMapper objectMapper=new ObjectMapper();
		       		try {
						docJson= objectMapper.readValue(doc,document.class);
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           if(docJson.getTitle() != null) 
		           {
		        	   document.setTitle(docJson.getTitle());
		           }
		           if(docJson.getDescription() != null) {
		        	   document.setDescription(docJson.getDescription());
		           }
		           if(docJson.getSubject() != null) {
		        	   document.setSubject(docJson.getSubject());
		           }
		           if(docJson.getName() != null) {
		        	   document.setName(docJson.getName());
		           }
		           if (file == null) {
		        	   document d= service.FilldataWithoutFile(document);
			           repo.save(d); 
			           return repo.save(d);
		           }
		           if(file != null ) {
		        	   document d= service.Filldata(document, file);
			           repo.save(d);  
			           return repo.save(d);
		           }
		        return repo.save(document);
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
	
	
	@RequestMapping(value="/documents/{Id}",produces = "application/json",
			method =  RequestMethod.DELETE)
	   public ResponseEntity<Object>deleteDocument(@PathVariable Integer Id) {
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
	
	
	public File convert(MultipartFile file) throws IOException
	 {    
	   File convFile = new File(file.getOriginalFilename());
	   convFile.createNewFile(); 
	   FileOutputStream fos = new FileOutputStream(convFile); 
	   fos.write(file.getBytes());
	   fos.close(); 
	   return convFile;
	 }
	
	
	@RequestMapping(method=RequestMethod.GET,value="/documentsParentsId/{Id}"
			,produces = "application/json")
	public ResponseEntity<Object> getDocumentParentId(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		List<document> response=null;
		List<document> newresp [] ;
		List<document> myList = new ArrayList<>();
		try {
		  response=service.listAll();
		  for(int i=0;i<response.size();i++) {
			   document dc=response.get(i);
			   if ((dc.getParentFolderId()== Id) && !(dc instanceof documentHistory) && !(dc instanceof Favoris)) {
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
	
	
	@RequestMapping(method=RequestMethod.GET,value="/Buffer/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getBufferedArray(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		byte[] resp=null;
		try {
			resp=service.get(Id).getData();
			
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
	
	@RequestMapping(method=RequestMethod.GET,value="/file/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getFileById(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		document resp=null;
		try {
			resp=service.get(Id);
			FileUtils.writeByteArrayToFile(new File("/back-end-GED/uploads"), resp.getData());
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
	
	@RequestMapping(method=RequestMethod.GET,value="/documentSearch/{value}"
			,produces = "application/json")
	public ResponseEntity<Object> getDocumentLookingFor(@PathVariable String value) {
		HttpStatus statusCode = HttpStatus.OK;
		List<document> response=null;
		document resp = null;
		List<document> myList = new ArrayList<>();
		String[] parts = value.split("-");
		String search=null;
		String user=null;
		String tr=null;
		String tr1=null;
		String[] parts1=null;
		search=parts[0];
		user=parts[1].replaceAll("\\s", "");
		int k=0;
		try {
		  response=service.listAll();
		  for(int i=0;i<response.size();i++) {
			   document dc=response.get(i);
			   if(dc.getUserName() != null)  {
				   parts1=dc.getName().split(".");
				   tr= dc.getUserName().replaceAll("\\s", "");
			  if (tr.equals(user) && dc.getName().contains(search) ) {
				  resp=service.get(dc.getId());
				  myList.add(resp);
				  break;
			
			   }
			   
		  }}
		} catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(myList,statusCode);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/stats"
			,produces = "application/json")
	public ResponseEntity<Object> getStats() {
		HttpStatus statusCode = HttpStatus.OK;
		List<document> response=null;
		try {
		  response=service.listAll();
		} catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response,statusCode);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/checkDocumentExistOrNot/{data}"
			,produces = "application/json")
	public ResponseEntity<Object> checkDocumentExistOrNot(@PathVariable String data) {
		HttpStatus statusCode = HttpStatus.OK;
		boolean r=false;
		String[] parts = data.split("-");
		String nameUser = parts[0];
		String nameDocument = parts[1];
		List<document> response=null;
		List<document> myList=new ArrayList<>();
		String p;
		String k;
		try {
		  response = service.listAll();
		  
		  for(int i=0;i<response.size();i++) {
			  document dc=response.get(i);
			  p=dc.getName().replaceAll("\\s", "");
			  k=dc.getUserName().replaceAll("\\s+$", "");
			  if(p.equals(nameDocument.replaceAll("\\s", "")) && k.equals(nameUser.replaceAll("\\s+$", "")) 
				&& !(dc instanceof documentValidation) && !(dc instanceof documentHistory )
				&& !(dc instanceof Favoris) 	  ) {
				  r=true;
			  }
		  }
		} catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(r,statusCode);
	}	
	
	
	
}
