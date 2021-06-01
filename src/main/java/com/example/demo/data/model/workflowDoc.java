package com.example.demo.data.model;

import java.util.Arrays;

public class workflowDoc extends document {

	public String workflowMembers;
	
	public String  category;

	public workflowDoc(String name, String creationDate, String description, String size, int parentFolderId, int Id,
			String workflowMembers, String category) {
		super(name, creationDate, description, size, parentFolderId, Id);
		this.workflowMembers = workflowMembers;
		this.category = category;
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
				+ category + ", subject=" + subject + ", format=" + format + ", title=" + title
				+ ", Id=" + Id + ", name=" + name + ", creationDate=" + creationDate + ", description=" + description
				+ ", size=" + size + ", parentFolderId=" + parentFolderId + ", getData()=" + Arrays.toString(getData())
				+ ", getSubject()=" + getSubject() + ", getFormat()=" + getFormat() + ", getTitle()=" + getTitle()
				+ ", toString()=" + super.toString() + ", getName()=" + getName() + ", getCreationDate()="
				+ getCreationDate() + ", getDescription()=" + getDescription() + ", getSize()=" + getSize()
				+ ", getParentFolderId()=" + getParentFolderId() + ", getId()=" + getId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
}
