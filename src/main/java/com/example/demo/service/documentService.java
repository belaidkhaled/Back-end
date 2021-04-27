package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.data.dao.documentRepo;
import com.example.demo.data.entity.document;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.util.internal.ResourcesUtil;


@Service
public class documentService {

	@Autowired
	private documentRepo repo ;
	
	public List<document> listAll(){
		return repo.findAll();
	}
	
	public document save(document doc) {
		repo.save(doc);
		return doc;
	}
	
	public document get(int Id) {
		
		return repo.findById(Id).get();
	}
	
	public boolean delete(int Id) {
	   repo.deleteById(Id);
	   document doc=repo.findById(Id).get();
	   if(doc == null) {
		   return true;
	   }
	   else return false;
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
			
			File f=convert(file);
			String path=f.getAbsolutePath();
			docJson.setPath(path);
			String name = StringUtils.cleanPath(file.getOriginalFilename());
			docJson.setFormat(file.getContentType());
			docJson.setName(name);
			docJson.setData(file.getBytes());
			docJson.setNewUrl(urlEncode(file.getBytes()));
			
	     
			docJson.setSize(FileUtils.byteCountToDisplaySize((long) file.getBytes().length));
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			docJson.setCreationDate(dateFormat.format(cal.getTime()));
			
			
			
			
			repo.save(docJson);  
			return docJson;    
    }
	
	public ResponseEntity<Resource> downloadFile(int fileId) throws Exception 
    {
        try
        {
        	document dc=get(fileId);
            return ResponseEntity.ok()
            		.contentType(org.springframework.http.MediaType.parseMediaType(dc.getFormat()))
            		.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, "attachment; filename=\"" + dc.getName() + "\"" )
                    .body(new ByteArrayResource(dc.getData()));
        }   
        catch(Exception e)
        {
            throw new Exception("Error downloading file");
        }
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
	 
	 public document Filldata(document doc,MultipartFile file) {
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
	 
	 
	 public document FilldataWithoutFile(document doc) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				doc.setCreationDate(dateFormat.format(cal.getTime()));
				return doc;
	 }
	 
	 
	 public static String urlEncode(byte[] binary) {
	        if (binary == null) {
	            return null;
	        }
	        try { // we use a base encoding that accepts all byte values
	            return URLEncoder.encode(new String(binary, "ISO8859_1"), "ISO8859_1");
	        } catch (UnsupportedEncodingException e) {
	            // this cannot happen with ISO8859_1 = Latin1
	            e.printStackTrace();
	            return null;

	        }
	    }
	 
	 
	
}
