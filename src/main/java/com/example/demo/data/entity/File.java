package com.example.demo.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
public class File {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	
	public int id;
	
	
	public String name ;
	
	
	public String creationDate;
	

	public String description ;
	
	
	public String size;
	
	
	
	
	
	public int parentFolderId;
	
	
	
	
	protected File() {
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "File [name=" + name + ", creationDate=" + creationDate + ", description=" + description + ", size="
				+ size + ", parentFolderId=" + parentFolderId +  "]";
	}
	public File(String name, String creationDate, String description, String size, int parentFolderId, int Id) {
		this.name = name;
		this.creationDate = creationDate;
		this.description = description;
		this.size = size;
		this.parentFolderId = parentFolderId;
		this.id=Id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getParentFolderId() {
		return parentFolderId;
	}
	public void setParentFolderId(int parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
