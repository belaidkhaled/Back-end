package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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
import com.example.demo.data.entity.Favoris;
import com.example.demo.data.entity.Folder;
import com.example.demo.data.entity.currentUser;
import com.example.demo.service.documentService;
import com.example.demo.service.folderService;
import com.example.demo.data.entity.document;
import com.example.demo.data.entity.documentHistory;
import com.example.demo.data.entity.user;
import com.example.demo.data.entity.workflowDoc;

@CrossOrigin
@Controller
@RestController
public class folderController {
	@Autowired
	private folderService service;
	
	@Autowired
	private folderRepo repo;
	
	@Autowired
	private documentService servicedoc;
	
	
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
		        	   if(folder.getDescription() != null) {
				           Folder.setDescription(folder.getDescription());
		        	   }
		        	   if(folder.getCategorie() != null) {
		        		   Folder.setCategorie(folder.getCategorie());
		        		   
		        	   }
		        	   if(folder.getName() != null) {
		        		 Folder.setName(folder.getName());
		        	   }
		        	 
		           
		           
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
		List<Folder> response=null;
		List<document> res=null;
		List<Folder> myList = new ArrayList<>();
		List<document> myListdoc = new ArrayList<>();
		boolean resp = false;
		int i1=0;
		try {
			service.delete(Id);
			response=service.listAll();
			res=servicedoc.listAll();
			  for(int i=0;i<response.size();i++) {
				   Folder dc=response.get(i);
				   if (dc.getParentFolderId()== Id) {
					  service.delete(dc.getId());
					  }
			  }
			  for(int j=0;j<res.size();j++) {
				   document dc1=res.get(j);
				   if (dc1.getParentFolderId()== Id) {
					   i1=dc1.getId();
					   servicedoc.delete(i1);
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
	
	
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/fodlersParentsId/{Id}"
			,produces = "application/json")
	public ResponseEntity<Object> getFolderParentId(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		List<Folder> response=null;
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
	
	
	@RequestMapping(method=RequestMethod.GET,value="/FolderSearch/{value}"
			,produces = "application/json")
	public ResponseEntity<Object> getFolderLookingFor(@PathVariable String value) {
		HttpStatus statusCode = HttpStatus.OK;
		List<Folder> response=null;
		Folder resp = null;
		List<Folder> myList = new ArrayList<>();
		String[] parts = value.split("-");
		String search=null;
		String user=null;
		String tr=null;
		search=parts[0].replaceAll("\\s", "");
		user=parts[1].replaceAll("\\s", "");
		try {
		  response=service.listAll();
		  for(int i=0;i<response.size();i++) {
			   Folder dc=response.get(i);
			   if(dc.getUserName() != null) {
				  tr= dc.getUserName().replaceAll("\\s", "");
			  if (dc.getName().replaceAll("\\s", "").equals(search) && tr.equals(user)) {
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
	
	@RequestMapping(method=RequestMethod.GET,value="/FolderPathSearch/{id}"
			,produces = "application/json")
	public ResponseEntity<Object> getPathLookingFor(@PathVariable int id) {
		HttpStatus statusCode = HttpStatus.OK;
		Folder response = null;
		List<Folder> resp=null;
		List<String> arr =new ArrayList<>();
		Folder dc = null;
		String path = null;
		try {
			response=service.get(id);
			arr.add(response.getName());
			resp=service.listAll();
			while(response.getParentFolderId() !=0) {
			  for(int i=0;i<resp.size();i++) {
				   dc=resp.get(i);
				   if (response.getParentFolderId()== dc.getId()) {
					   arr.add(dc.getName());
					   response=service.get(dc.getId()); 
				   }
				 }
			  }  
			path=arr.get(arr.size()-1);
			for(int i=arr.size()-2;i>=0;i--) {
				path=path+"/"+ arr.get(i);
			}
			}
		
		catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(path,statusCode);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/fodlersCategories/{categorie}"
			,produces = "application/json")
	public ResponseEntity<Object> getFoldersCategories(@PathVariable String categorie) throws UnsupportedEncodingException {
		HttpStatus statusCode = HttpStatus.OK;
		List<Folder> response=null;
		List<Folder> myList = new ArrayList<>();
		String decoded = URLDecoder.decode(categorie, "UTF-8");
		try {
		  response=service.listAll();
		  for(int i=0;i<response.size();i++) {
			   Folder dc=response.get(i);
			   if( dc.getCategorie() != null) {
			   if (dc.getCategorie().equals(decoded)) {
				   myList.add(dc);
			   }}
		  }
		} catch(EntityNotFoundException e) {
			statusCode=HttpStatus.GONE;
		} catch(Exception e) {
			e.printStackTrace();
			statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(myList,statusCode);
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/foldersHead/{user}"
			,produces = "application/json")
	public ResponseEntity<Object> getFolderHead(@PathVariable String user) throws UnsupportedEncodingException  {
		HttpStatus statusCode = HttpStatus.OK;
		List<Folder> response=null;
		List<Folder> myList = new ArrayList<>();
		String test1 =null ;
		String test2=null;
		String decoded = URLDecoder.decode(user, "UTF-8");
		try {
		  response=service.listAll();
		  for(int i=0;i<response.size();i++) {
			   Folder dc=response.get(i);
			   if(dc.getUserName() != null ) {
				  test1= dc.getUserName().replaceAll("\\s", "");
				  test2=decoded.replaceAll("\\s", "");
				  if(test1.equals(test2) && dc.getParentFolderId() ==0) {
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
	
	
	// get the radar charts data for every user
	@RequestMapping(value="/radarChartJS/{data}",produces = "application/json",
			method =  RequestMethod.GET)
	   public ResponseEntity<Object>getCharts(@PathVariable String data) {
		HttpStatus statusCode = HttpStatus.OK;
		boolean r=false;
		String username = data;
		List<document> myList1 = new ArrayList<>();
		List<document> response1=null;
		List<Folder> response=null;
		List<Folder> myList = new ArrayList<>();
		boolean resp = false;
		String p;
		String k;
		int[] arr=new int[5] ;
		int i1=0;
		try {
			response1=servicedoc.listAll();
			response=service.listAll();
			  for(int i=0;i<response.size();i++) {
				   Folder dc=response.get(i);
				   p=dc.getUserName().replaceAll("\\s", "");
				   for(int j=0;j<response1.size();j++) {
					   document d=response1.get(j);
				  
				   if(dc.getCategorie() !=null) {
					   k=dc.getCategorie().replaceAll("\\s", "");
				   if (p.equals(username.replaceAll("\\s", "")) ) {
					 if(k.equals("Reception") && d.getParentFolderId()==dc.getId() 
							 && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
						 arr[0]+=1;
					 }
					 if(k.equals("ficheclient") && d.getParentFolderId()==dc.getId() && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
						 arr[1]+=1;
					 }
					 if(k.equals("fichefournisseur") && d.getParentFolderId()==dc.getId() && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
						 arr[2]+=1;
					 }
					 if(k.equals("facture") && d.getParentFolderId()==dc.getId() && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
						 arr[3]+=1;
					 }
					 if(k.equals("Demandedeprix") && d.getParentFolderId()==dc.getId() && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
						 arr[4]+=1;
					 }
					  } }
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
		return new ResponseEntity<>(arr,statusCode);
	  }
	
	
	
	// get the Bar chart data
		@RequestMapping(value="/barChartJS",produces = "application/json",
				method =  RequestMethod.GET)
		   public ResponseEntity<Object>getBarCharts() {
			HttpStatus statusCode = HttpStatus.OK;
			boolean r=false;
			List<document> myList1 = new ArrayList<>();
			List<document> response1=null;
			List<Folder> response=null;
			List<Folder> myList = new ArrayList<>();
			boolean resp = false;
			String p;
			String k;
			int[] arr=new int[5] ;
			int i1=0;
			try {
				response1=servicedoc.listAll();
				response=service.listAll();
				  for(int i=0;i<response.size();i++) {
					   Folder dc=response.get(i);
					  
					   for(int j=0;j<response1.size();j++) {
						   document d=response1.get(j);
					  
					   if(dc.getCategorie() !=null) {
						   k=dc.getCategorie().replaceAll("\\s", "");
					
						 if(k.equals("Reception") && d.getParentFolderId()==dc.getId() 
								 && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
							 arr[0]+=1;
						 }
						 if(k.equals("ficheclient") && d.getParentFolderId()==dc.getId() && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
							 arr[1]+=1;
						 }
						 if(k.equals("fichefournisseur") && d.getParentFolderId()==dc.getId() && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
							 arr[2]+=1;
						 }
						 if(k.equals("facture") && d.getParentFolderId()==dc.getId() && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
							 arr[3]+=1;
						 }
						 if(k.equals("Demandedeprix") && d.getParentFolderId()==dc.getId() && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
							 arr[4]+=1;
						 }
						  } 
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
			return new ResponseEntity<>(arr,statusCode);
		    
		  }
		
		//get top chart value
		
		@RequestMapping(value="/topCharts/{user}",produces = "application/json",
				method =  RequestMethod.GET)
		   public ResponseEntity<Object>getTopCharts(@PathVariable String user) {
			HttpStatus statusCode = HttpStatus.OK;
			boolean r=false;
			List<document> myList1 = new ArrayList<>();
			List<document> response1=null;
			List<Folder> response=null;
			List<Folder> myList = new ArrayList<>();
			boolean resp = false;
			String p;
			String k;
			k=user.replaceAll("\\s", "");
			int[] arr=new int[2] ;
			int i1=0;
			int i2=0;
			try {
				response1=servicedoc.listAll();
				response=service.listAll();
				  for(int i=0;i<response.size();i++) {
					   Folder dc=response.get(i);
					   if(dc.getUserName().replaceAll("\\s", "").equals(k)) {
						   i1+=1;
					   }
					  
				  }
				  for(int i=0;i<response.size();i++) {
					   Folder dc=response.get(i);
					  
					   for(int j=0;j<response1.size();j++) {
						   document d=response1.get(j);
					  
						 if(dc.getUserName().replaceAll("\\s", "").equals(k) && d.getParentFolderId()==dc.getId() 
								 && !(d instanceof documentHistory) && !(d instanceof Favoris) ) {
							i2+=1;
						 }
						
						  
				  }
				  }
				  
				   arr[0]=i1;
				   arr[1]=i2;
			}
			catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
			}
			catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity<>(arr,statusCode);
		    
		  }
		
		
		@RequestMapping(method=RequestMethod.GET,value="/checkFolderExistOrNot/{data}"
				,produces = "application/json")
		public ResponseEntity<Object> checkFolderExistOrNot(@PathVariable String data) {
			HttpStatus statusCode = HttpStatus.OK;
			boolean r=false;
			String[] parts = data.split("-");
			String nameUser = parts[0];
			String nameFolder = parts[1];
			List<Folder> response=null;
			List<Folder> myList=new ArrayList<>();
			String p;
			String k;
			try {
			  response = service.listAll();
			  
			  for(int i=0;i<response.size();i++) {
				  Folder dc=response.get(i);
				  p=dc.getName().replaceAll("\\s", "");
				  k=dc.getUserName().replaceAll("\\s", "");
				  if(p.equals(nameFolder.replaceAll("\\s", "")) && k.equals(nameUser.replaceAll("\\s", "")) ) {
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
