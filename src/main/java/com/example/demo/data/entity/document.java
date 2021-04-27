package com.example.demo.data.entity;

import java.net.URL;
import java.nio.file.Path;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="documents")
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class document extends File {
	public document(String name, String creationDate, String description, String size, int parentFolderId, int Id) {
		super(name, creationDate, description, size, parentFolderId, Id);
		// TODO Auto-generated constructor stub
	}
   
	@Column(name = "subject")
	public String  subject;
	
	@Column(name = "format", nullable=false)
	public String  format;
	
	@Column(name = "title",nullable=true)
	public String  title;
	
	@Column(name = "Path")
	public String  path;
	
	@Column(name = "UpdatedDate")
	public String  UpdatedDate;
	
	@Lob
	@Column(name = "url")
	public String url;
	
	

	public String getNewUrl() {
		return url;
	}


	public void setNewUrl(String newUrl) {
		this.url = newUrl;
	}


	public String getUpdatedDate() {
		return UpdatedDate;
	}


	public void setUpdatedDate(String updatedDate) {
		UpdatedDate = updatedDate;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}

	@Lob
    @Column(name = "data")
    private byte[] data;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="folder_Id")
	private Folder folder;
	
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
			String subject, String format, String title,byte[] data,String UpdatedDate ) {
		super(name, creationDate, description, size, parentFolderId,Id);
		this.subject = subject;
		this.format = format;
		this.title = title;
		this.data=data;	
		this.UpdatedDate=UpdatedDate;
	}
   
	public document() {
		super();
	}
   
	@Override
	public String toString() {
		return "document [subject=" + subject + ", format=" + format + ", title=" + title + 
				", data=" + data +", UpdatedDate=" + UpdatedDate+"]";
	}
	
}
