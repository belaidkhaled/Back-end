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
import com.example.demo.data.dao.documentValidationRepo;
import com.example.demo.data.entity.document;
import com.example.demo.data.entity.documentHistory;
import com.example.demo.data.entity.documentValidation;
import com.example.demo.data.entity.trackArray;
import com.example.demo.data.entity.workflowData;
import com.example.demo.data.entity.workflowDoc;
import com.example.demo.service.documentService;
import com.example.demo.service.documentValidationService;

@CrossOrigin
@Controller
@RestController
public class documentValidationController {
	
	@Autowired
	private documentValidationService service;
	
	@Autowired
	private documentValidationRepo repo;
	
	@RequestMapping(method= {RequestMethod.POST} ,value="/documentsToValidate",
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {"application/json","application/pdf"})
	public ResponseEntity<Object> createDocumentToValidate(@RequestPart("document") String doc,
			        @RequestPart("file") MultipartFile  file)  {
		HttpStatus statusCode = HttpStatus.OK;
		documentValidation response=null;
		try {
			documentValidation documentJson=service.getJson(doc,file);
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
	
	

	@RequestMapping(method=RequestMethod.GET,value="/alldocumentUploadedTovalidated"
			,produces = "application/json")
	public ResponseEntity<Object> getAllWorkflow() {
		HttpStatus statusCode = HttpStatus.OK;
		List<documentValidation> response=null;
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
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/workflowTrack/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getWorkflowTrackById(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		documentValidation response=null;
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
	
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/workflowTrackMinus/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> updateMinusTrackValidation(@PathVariable Integer Id) {
		HttpStatus statusCode = HttpStatus.OK;
		documentValidation response=null;
		try {
			response=service.get(Id);
			if(response.trackValidation ==null) {
				response.trackValidation=0;
				response.trackValidation=response.trackValidation + 1;
			}
			else {
				response.trackValidation=response.trackValidation - 1;
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
	
	
	

	@RequestMapping(method=RequestMethod.GET,value="/workflotrackwarray/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getTrackArrayById(@PathVariable Integer Id
			) {
		HttpStatus statusCode = HttpStatus.OK;
		documentValidation response=null;
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
	
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/workflowarray/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> getWorkflowArrayById(@PathVariable Integer Id,
			@RequestBody trackArray trk) {
		HttpStatus statusCode = HttpStatus.OK;
		documentValidation response=null;
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
	
	//for validated and not validated
	@RequestMapping(method=RequestMethod.PUT,value="/workflowarraytonull/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> putWorkflowArrayToNull(@PathVariable Integer Id,
			@RequestBody trackArray trk) {
		HttpStatus statusCode = HttpStatus.OK;
		documentValidation response=null;
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
	
	//for bloking the state
	@RequestMapping(method=RequestMethod.PUT,value="/workflowarraytoBlocked/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> putWorkflowArrayblockedState(@PathVariable Integer Id,
			@RequestBody trackArray trk) {
		HttpStatus statusCode = HttpStatus.OK;
		documentValidation response=null;
		try {
			
			response=service.get(Id);
			if(response.trackArray==null) {
				int[] myIntArray = new int[7];
				response.trackArray=myIntArray;
			}
			response.trackArray[trk.getIndex()]=2;
			
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
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/AddComment/{Id}"
			,produces="application/json")
	public ResponseEntity<Object> AddComment(@PathVariable Integer Id,
			@RequestBody workflowData wd) {
		HttpStatus statusCode = HttpStatus.OK;
		documentValidation response=null;
		try {
			response=service.get(Id);
			if(wd.level.equals("1")) {
				response.setCommentL1(wd.comment);
			}
			if(wd.level.equals("2")) {
				response.setCommentL2(wd.comment);
			}
			if(wd.level.equals("3")) {
				response.setCommentL3(wd.comment);
			}
			if(wd.level.equals("4")) {
				response.setCommentL4(wd.comment);
			}
			if(wd.level.equals("5")) {
				response.setCommentL5(wd.comment);
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
	
	
	


	@RequestMapping(method=RequestMethod.GET,value="/listDocInvolved/{name0}"
			,produces="application/json")
	public ResponseEntity<Object> getListOfdoctoBeValidated(@PathVariable String name0
			) {
		HttpStatus statusCode = HttpStatus.OK;
		List<documentValidation> response;
		List<documentValidation> myList=new ArrayList<>();
		List<String> myList0=new ArrayList<>();
		String name= name0.replaceAll("\\s+$", "");
		String msg1=null;
		try {
			response=service.listAll();
			for(int i=0;i<response.size();i++) {
				documentValidation dc=response.get(i);
				if(dc.getTrackArray() == null) {
					dc.setTrackArray(new int[6]);
				}
				if(dc.Level1 != null ) {
					if(dc.Level1.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[1] ==0 ) {
						myList.add(dc);
						msg1=dc.getUserName() +"is waiting for you to validate the "
					+ dc.getName() +" at level number 1" ;
						myList0.add(msg1);
					}
			
				}
				
				if(dc.Level2 != null) {
					if(dc.getHierarchy().equals("true")) {
						if(dc.Level2.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[2] ==0 && dc.getTrackArray()[1] ==1) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 2" ;
										myList0.add(msg1);
						}
						
					}
					if(dc.getHierarchy().equals("false")) {
						if(dc.Level2.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray() ==null) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 2" ;
										myList0.add(msg1);
						}
						if(dc.getTrackArray() !=null) {
						if(dc.Level2.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[2] ==0) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 2" ;
										myList0.add(msg1);
						}
						}
						
					}
				}
				if(dc.Level3 != null) {
					if(dc.getHierarchy().equals("true")) {
						if(dc.Level3.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[3] ==0 && dc.getTrackArray()[1] ==1 && dc.getTrackArray()[2] ==1) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 3" ;
										myList0.add(msg1);
						}
					}
					if(dc.getHierarchy().equals("false")) {
						if(dc.Level3.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray() ==null) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 3" ;
										myList0.add(msg1);
						}
						if(dc.getTrackArray() !=null) {
						if(dc.Level3.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[3] ==0 ) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 3" ;
										myList0.add(msg1);
						}
						
					}}
				}

				if(dc.Level4 != null) {
					if(dc.getHierarchy().equals("true")) {
						if(dc.Level4.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[4] ==0 && dc.getTrackArray()[3] ==1 && dc.getTrackArray()[2] ==1&& dc.getTrackArray()[1] ==1) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 4" ;
										myList0.add(msg1);
						}
					}
					if(dc.getHierarchy().equals("false")) {
						if(dc.Level4.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray() ==null) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 4" ;
										myList0.add(msg1);
						}
						if(dc.getTrackArray() !=null) {
						if(dc.Level4.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[4] ==0) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 3" ;
										myList0.add(msg1);
						}
						}
					}
				}
				if(dc.Level5 != null) {
					if(dc.getHierarchy().equals("true")) {
						if(dc.Level5.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[5] ==0 && dc.getTrackArray()[4] ==1 && dc.getTrackArray()[3] ==1 && dc.getTrackArray()[2] ==1 && dc.getTrackArray()[1] ==1) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 5" ;
										myList0.add(msg1);
						}
					}
					if(dc.getHierarchy().equals("false")) {
						if(dc.Level5.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray() ==null) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 5" ;
										myList0.add(msg1);
						}
						if(dc.getTrackArray() !=null) {
						if(dc.Level5.replaceAll("\\s+$", "").equals(name) && dc.getTrackArray()[5] ==0) {
							myList.add(dc);
							msg1=dc.getUserName() +"is waiting for you to validate the "
									+ dc.getName() +" at level number 3" ;
										myList0.add(msg1);
						}
						
					}}
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
		return new ResponseEntity<>(myList0,statusCode);
	}

	
	

}
