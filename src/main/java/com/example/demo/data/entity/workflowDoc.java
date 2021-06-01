package com.example.demo.data.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="workflow")
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class workflowDoc extends document {

	public workflowDoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public workflowDoc(String name, String creationDate, String description, String size, int parentFolderId, int Id,
			String subject, String format, String title, byte[] data, String UpdatedDate) {
		super(name, creationDate, description, size, parentFolderId, Id, subject, format, title, data, UpdatedDate);
		// TODO Auto-generated constructor stub
	}

	public workflowDoc(String name, String creationDate, String description, String size, int parentFolderId, int Id) {
		super(name, creationDate, description, size, parentFolderId, Id);
		// TODO Auto-generated constructor stub
	}

	@Column(name = "workflowMembers")
	public String workflowMembers;
	
	@Override
	public String toString() {
		return "workflowDoc [workflowMembers=" + workflowMembers + ", category="
				+ category + ", subject=" + subject + ", format=" + format + ", title=" + title
				+ ", path=" + path + ", UpdatedDate=" + UpdatedDate + ", url=" + url + ", id=" + id + ", name=" + name
				+ ", creationDate=" + creationDate + ", description=" + description + ", size=" + size
				+ ", parentFolderId=" + parentFolderId + ", getWorkflowMembers()="
				+ getWorkflowMembers() + ", getCategory()=" + getCategory()
				+ ", getNewUrl()=" + getNewUrl() + ", getUpdatedDate()=" + getUpdatedDate() + ", getPath()=" + getPath()
				+ ", getData()=" + Arrays.toString(getData()) + ", getSubject()=" + getSubject() + ", getFormat()="
				+ getFormat() + ", getTitle()=" + getTitle() + ", toString()=" + super.toString() + ", getName()="
				+ getName() + ", getCreationDate()=" + getCreationDate() + ", getDescription()=" + getDescription()
				+ ", getSize()=" + getSize() + ", getParentFolderId()=" + getParentFolderId() + ", getId()=" + getId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public String getWorkflowMembers() {
		return workflowMembers;
	}

	public void setWorkflowMembers(String workflowMembers) {
		this.workflowMembers = workflowMembers;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "category")
	public String  category;
}
