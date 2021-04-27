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
	public int  version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public documentHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public documentHistory(String name, String creationDate, String description, String size, int parentFolderId,
			int Id, String subject, String format, String title, byte[] data, String UpdatedDate) {
		super(name, creationDate, description, size, parentFolderId, Id, subject, format, title, data, UpdatedDate);
		// TODO Auto-generated constructor stub
	}

	public documentHistory(String name, String creationDate, String description, String size, int parentFolderId,
			int Id) {
		super(name, creationDate, description, size, parentFolderId, Id);
		// TODO Auto-generated constructor stub
	}

	public documentHistory(String name, String creationDate, String description, String size, int parentFolderId,
			int Id, int version) {
		super(name, creationDate, description, size, parentFolderId, Id);
		this.version = version;
	}

	@Override
	public String toString() {
		return "documentHistory [version=" + version + ", subject=" + subject + ", format=" + format + ", title="
				+ title + ", path=" + path + ", UpdatedDate=" + UpdatedDate + ", Id=" + id + ", name=" + name
				+ ", creationDate=" + creationDate + ", description=" + description + ", size=" + size
				+ ", parentFolderId=" + parentFolderId + ", getVersion()=" + getVersion() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", getPath()=" + getPath() + ", getData()=" + Arrays.toString(getData())
				+ ", getSubject()=" + getSubject() + ", getFormat()=" + getFormat() + ", getTitle()=" + getTitle()
				+ ", toString()=" + super.toString() + ", getName()=" + getName() + ", getCreationDate()="
				+ getCreationDate() + ", getDescription()=" + getDescription() + ", getSize()=" + getSize()
				+ ", getParentFolderId()=" + getParentFolderId() + ", getId()=" + getId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	

}
