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

	@Column(name="userName")
	public String userName ;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "workflowMembers")
	public String workflowMembers;
	
	@Column(name = "comment")
	public String comment;
	
	@Column(name = "commentL1")
	public String commentL1;
	
	@Column(name = "commentL2")
	public String commentL2;
	
	@Column(name = "commentL3")
	public String commentL3;
	
	@Column(name = "commentL4")
	public String commentL4;
	
	@Column(name = "commentL5")
	public String commentL5;
	
	public String getCommentL1() {
		return commentL1;
	}

	public void setCommentL1(String commentL1) {
		this.commentL1 = commentL1;
	}

	public String getCommentL2() {
		return commentL2;
	}

	public void setCommentL2(String commentL2) {
		this.commentL2 = commentL2;
	}

	public String getCommentL3() {
		return commentL3;
	}

	public void setCommentL3(String commentL3) {
		this.commentL3 = commentL3;
	}

	public String getCommentL4() {
		return commentL4;
	}

	public void setCommentL4(String commentL4) {
		this.commentL4 = commentL4;
	}

	public String getCommentL5() {
		return commentL5;
	}

	public void setCommentL5(String commentL5) {
		this.commentL5 = commentL5;
	}

	@Column(name = "trackValidation")
	public Integer trackValidation ;
	
	@Column(name = "trackArray")
	public int[] trackArray;
	
	public int[] getTrackArray() {
		return trackArray;
	}

	public void setTrackArray(int[] trackArray) {
		this.trackArray = trackArray;
	}

	public Integer getTrackValidation() {
		return trackValidation;
	}

	public void setTrackValidation(Integer trackValidation) {
		this.trackValidation = trackValidation;
	}

	public String getComment() {
		return comment;
	}
	
	@Column(name = "hierarchy")
	public String hierarchy;

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "workflowDoc [workflowMembers=" + workflowMembers + ", category="
				+ category +  ", comment=" + trackArray +  ", trackArray=" 
						+ comment + hierarchy +  ", hierarchy="+ ", subject=" + subject + ", format=" + format + ", title=" + title
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
