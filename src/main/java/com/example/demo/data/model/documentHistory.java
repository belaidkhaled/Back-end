package com.example.demo.data.model;

import java.util.Arrays;

public class documentHistory extends document {
	
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
			int Id, String subject, String format, String title, byte[] data) {
		super(name, creationDate, description, size, parentFolderId, Id, subject, format, title, data);
		// TODO Auto-generated constructor stub
	}

	public documentHistory(String name, String creationDate, String description, String size, int parentFolderId,
			int Id) {
		super(name, creationDate, description, size, parentFolderId, Id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "documentHistory [version=" + version + ", subject=" + subject + ", format=" + format + ", title="
				+ title + ", Id=" + Id + ", name=" + name + ", creationDate=" + creationDate + ", description="
				+ description + ", size=" + size + ", parentFolderId=" + parentFolderId + ", getVersion()="
				+ getVersion() + ", getData()=" + Arrays.toString(getData()) + ", getSubject()=" + getSubject()
				+ ", getFormat()=" + getFormat() + ", getTitle()=" + getTitle() + ", toString()=" + super.toString()
				+ ", getName()=" + getName() + ", getCreationDate()=" + getCreationDate() + ", getDescription()="
				+ getDescription() + ", getSize()=" + getSize() + ", getParentFolderId()=" + getParentFolderId()
				+ ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
