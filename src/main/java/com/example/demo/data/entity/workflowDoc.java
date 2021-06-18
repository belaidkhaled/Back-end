package com.example.demo.data.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="workflow")
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class workflowDoc {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public int id;

	@Column(name="category")
	public String category;
	
	@Column(name="levelNumber")
	public Integer levelNumber;
	
	public workflowDoc(int id, String category, Integer levelNumber, String created_At, String mainUser,
			String hierarchy, String level1, String level2, String level3, String level4, String level5,
			String commentSupervisor, String superviseur) {
		super();
		this.id = id;
		this.category = category;
		this.levelNumber = levelNumber;
		this.created_At = created_At;
		MainUser = mainUser;
		Hierarchy = hierarchy;
		Level1 = level1;
		Level2 = level2;
		Level3 = level3;
		Level4 = level4;
		Level5 = level5;
		CommentSupervisor = commentSupervisor;
		Superviseur = superviseur;
	}

	public Integer getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreated_At() {
		return created_At;
	}

	public void setCreated_At(String created_At) {
		this.created_At = created_At;
	}

	@Column(name="created_At")
	public String created_At;
	
	@Column(name="MainUser")
	public String MainUser;
	
	@Column(name="Hierarchy")
	public String Hierarchy;
	
	@Column(name="Level1")
	public String Level1;
	
	@Column(name="Level2")
	public String Level2;
	
	@Column(name="Level3")
	public String Level3;
	
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

	@Column(name="Level4")
	public String Level4;

	
	@Column(name="Level5")
	public String Level5;
	
	@Column(name="CommentSupervisor")
	public String CommentSupervisor;
	
	@Column(name="Superviseur")
	public String Superviseur;

	public String getSuperviseur() {
		return Superviseur;
	}

	public void setSuperviseur(String superviseur) {
		Superviseur = superviseur;
	}

	public workflowDoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public workflowDoc(String category, String mainUser, String hierarchy, String level1, String level2, String level3,
			String level4, String level5, String commentSupervisor,String created_At, String Superviseur ) {
		super();
		this.category = category;
		this.created_At=created_At;
		this.Superviseur=Superviseur;
		MainUser = mainUser;
		Hierarchy = hierarchy;
		Level1 = level1;
		Level2 = level2;
		Level3 = level3;
		Level4 = level4;
		Level5 = level5;
		CommentSupervisor = commentSupervisor;
	}

}
