package com.example.demo.service;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.documentRepo;
import com.example.demo.model.document;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class documentService {

	@Autowired
	private documentRepo repo ;
	
	public List<document> listAll(){
		return repo.findAll();
	}
	
	public void save(document doc) {
		repo.save(doc);
	}
	
	public document get(int Id) {
		return repo.findById(Id).get();
	}
	
	public void delete(int Id) {
		repo.deleteById(Id);
	}
	
	
	public document getJson(String doc,MultipartFile file) throws IOException {
       
			document docJson=new document();
		
			try {
				ObjectMapper objectMapper=new ObjectMapper();
				docJson= objectMapper.readValue(doc,document.class);
			}
			catch(IOException err){
				System.out.printf("error",err.toString());
			}
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
}
