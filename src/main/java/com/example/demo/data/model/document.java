package com.example.demo.data.model;


public class document extends File {

	public document(String name, String creationDate, String description, String size, int parentFolderId, int Id) {
		super(name, creationDate, description, size, parentFolderId, Id);
		// TODO Auto-generated constructor stub
	}
   
	public String  subject;
	
	
	public String  format;
	

	public String  title;
	
	
    private byte[] data;
	
	
	
	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}


	public String getSubject() {
		return subject;
	}
	
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public document(String name, String creationDate, String description,String size, int parentFolderId, int Id,
			String subject, String format, String title,byte[] data) {
		super(name, creationDate, description, size, parentFolderId,Id);
		this.subject = subject;
		this.format = format;
		this.title = title;
		this.data=data;	
	}
   
	public document() {
		super();
	}
   
	@Override
	public String toString() {
		return "document [subject=" + subject + ", format=" + format + ", title=" + title + 
				", data=" + data +"]";
	}
}

