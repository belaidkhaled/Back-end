package com.example.demo.data.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="documentHistory")
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class documentHistory extends document {
	
	@Column(name = "version")
	public int version;
	
	@Column(name = "originalId")
	public int  originalId;
	
	@Column(name="userName")
	public String userName ;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getOriginalId() {
		return originalId;
	}
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}
	
	

}
