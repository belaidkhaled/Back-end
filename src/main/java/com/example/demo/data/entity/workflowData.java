package com.example.demo.data.entity;

public class workflowData {
public String comment;

public String level;
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public workflowData() {
	super();
}
public workflowData(String comment, String level) {
	super();
	this.comment = comment;
	
	this.level = level;
}

public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}



}
