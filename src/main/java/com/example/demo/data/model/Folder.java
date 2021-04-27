package com.example.demo.data.model;





public class Folder extends File {

	protected Folder() {
	}

	public Folder(String name, String creationDate, String description, String size, int parentFolderId, int Id) {
		super(name, creationDate, description, size, parentFolderId, Id);
		// TODO Auto-generated constructor stub
	}
	
	public Long maxSize;
	
	

	public Long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public String toString() {
		return "Folder [maxSize=" + maxSize + ", name=" + name + ", CreationDate=" + creationDate + ", description="
				+ description + ", size=" + size + ", ParentFolderId=" + parentFolderId + ", FileId=" + Id
				+ ", getMaxSize()=" + getMaxSize() + ", getName()=" + getName() + ", toString()=" + super.toString()
				+ ", getCreationDate()=" + getCreationDate() + ", getDescription()=" + getDescription() + ", getSize()="
				+ getSize() + ", getParentFolderId()=" + getParentFolderId() + ", getFileId()=" + getId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}

