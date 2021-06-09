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
	

	public workflowDoc getJson(String doc,MultipartFile file) throws IOException {
       
			workflowDoc docJson=new workflowDoc();
		
			try {
				ObjectMapper objectMapper=new ObjectMapper();
				docJson= objectMapper.readValue(doc,workflowDoc.class);
			}
			catch(IOException err){
				System.out.printf("error",err.toString());
			}
			
			File f=convert(file);
			String path=f.getAbsolutePath();
			docJson.setPath(path);
			String name = StringUtils.cleanPath(file.getOriginalFilename());
			docJson.setFormat(file.getContentType());
			docJson.setName(name);
			docJson.setData(file.getBytes());
			
			docJson.setSize(FileUtils.byteCountToDisplaySize((long) file.getBytes().length));
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			docJson.setCreationDate(dateFormat.format(cal.getTime()));
			
			repo.save(docJson);  
			return docJson;    
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
	 
	 
	 public workflowDoc Filldata(workflowDoc doc,MultipartFile file) {
		 File f = null;
			try {
				f = convert(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   String path=f.getAbsolutePath();
			   doc.setPath(path);
				String name = StringUtils.cleanPath(file.getOriginalFilename());
				doc.setFormat(file.getContentType());
				doc.setName(name);
				try {
					doc.setData(file.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					doc.setSize(FileUtils.byteCountToDisplaySize((long) file.getBytes().length));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				doc.setCreationDate(dateFormat.format(cal.getTime()));
				return doc;
	 }
	
}
