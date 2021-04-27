package com.example.demo.data.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="favoris")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Favoris extends document {

	public Favoris() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Favoris(String name, String creationDate, String description, String size, int parentFolderId, int Id,
			String subject, String format, String title, byte[] data,String UpdatedDate) {
		super(name, creationDate, description, size, parentFolderId, Id, subject, format, title, data,UpdatedDate);
		// TODO Auto-generated constructor stub
	}

	public Favoris(String name, String creationDate, String description, String size, int parentFolderId, int Id) {
		super(name, creationDate, description, size, parentFolderId, Id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Favoris [subject=" + subject + ", format=" + format + ", title=" + title + ", Id=" + id + ", name="
				+ name + ", creationDate=" + creationDate + ", description=" + description + ", size=" + size
				+ ", parentFolderId=" + parentFolderId + ", getData()=" + Arrays.toString(getData()) + ", getSubject()="
				+ getSubject() + ", getFormat()=" + getFormat() + ", getTitle()=" + getTitle() + ", toString()="
				+ super.toString() + ", getName()=" + getName() + ", getCreationDate()=" + getCreationDate()
				+ ", getDescription()=" + getDescription() + ", getSize()=" + getSize() + ", getParentFolderId()="
				+ getParentFolderId() + ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	

}
