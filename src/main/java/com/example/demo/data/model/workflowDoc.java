package com.example.demo.data.model;

import java.util.Arrays;

public class workflowDoc extends document {

	public String workflowMembers;
	
	public String  category;
	
	public String  comment;
	
	public int[] trackArray;
	
	public int[] getTrackArray() {
		return trackArray;
	}

	public void setTrackArray(int[] trackArray) {
		this.trackArray = trackArray;
	}

	public String  hierarchy;
	
	public Integer trackValidation ;

	public Integer getTrackValidation() {
		return trackValidation;
	}

	public void setTrackValidation(Integer trackValidation) {
		this.trackValidation = trackValidation;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public workflowDoc(String name, String creationDate, String description, String size, int parentFolderId, int Id,
			String workflowMembers, String category,String comment,String hierarchy,int trackValidation,int[] trackArray ) {
		super(name, creationDate, description, size, parentFolderId, Id);
		this.workflowMembers = workflowMembers;
		this.category = category;
		this.hierarchy = hierarchy;
		this.trackValidation = trackValidation;
		this.trackArray=trackArray;
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

	@Override
	public String toString() {
		return "workflowDoc [workflowMembers=" + workflowMembers + ", category="
				+ category + ", comment="+  comment +  ", trackValidation="+  trackValidation + ", hierarchy="+ hierarchy +", subject=" + subject + ", format=" + format + ", title=" + title
				+ ", Id=" + Id + ", name=" + name + ", creationDate=" + creationDate + ", description=" + description
				+ ", size=" + size + ", parentFolderId=" + parentFolderId + ", getData()=" + Arrays.toString(getData())
				+ ", getSubject()=" + getSubject() + ", getFormat()=" + getFormat() + ", getTitle()=" + getTitle()
				+ ", toString()=" + super.toString() + ", getName()=" + getName() + ", getCreationDate()="
				+ getCreationDate() + ", getDescription()=" + getDescription() + ", getSize()=" + getSize()
				+ ", getParentFolderId()=" + getParentFolderId() + ", getId()=" + getId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
}
