package com.example.demo.data.model;

import java.util.Arrays;

import javax.persistence.Column;

public class workflowDoc {

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getMainUser() {
		return MainUser;
	}


	public void setMainUser(String mainUser) {
		MainUser = mainUser;
	}


	public String getHierarchy() {
		return Hierarchy;
	}


	public void setHierarchy(String hierarchy) {
		Hierarchy = hierarchy;
	}


	public String getLevel1() {
		return Level1;
	}


	public void setLevel1(String level1) {
		Level1 = level1;
	}


	public String getLevel2() {
		return Level2;
	}


	public void setLevel2(String level2) {
		Level2 = level2;
	}


	public String getLevel3() {
		return Level3;
	}


	public void setLevel3(String level3) {
		Level3 = level3;
	}


	public String getLevel4() {
		return Level4;
	}


	public void setLevel4(String level4) {
		Level4 = level4;
	}


	public String getLevel5() {
		return Level5;
	}


	public void setLevel5(String level5) {
		Level5 = level5;
	}


	public String getCommentSupervisor() {
		return CommentSupervisor;
	}


	public void setCommentSupervisor(String commentSupervisor) {
		CommentSupervisor = commentSupervisor;
	}


	public int id;

	public String category;
	
	
	public String MainUser;
	
	
	public String Hierarchy;
	
	
	public String Level1;
	

	public String Level2;
	
	
	public String Level3;
	
	public String Level4;
	
	
	public String Level5;
	
	
	public String CommentSupervisor;
}
