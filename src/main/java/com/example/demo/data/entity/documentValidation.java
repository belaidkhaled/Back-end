package com.example.demo.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="documentValidation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class documentValidation extends document {
	
	@Column(name="Level1")
	public String Level1 ;
	

	@Column(name="hierarchy")
	public String hierarchy ;
	

	@Column(name="commentSupervisor")
	public String commentSupervisor ;
	
	
	public documentValidation(String name, String creationDate, String description, String size, int parentFolderId,
			int Id, String level1, String hierarchy, String commentSupervisor, String category, String commentL1,
			String commentL2, Integer trackValidation, Integer levelNumber, String commentL3, String commentL4,
			String commentL5, int[] trackArray, String level2, String level3, String level4, String level5) {
		super(name, creationDate, description, size, parentFolderId, Id);
		Level1 = level1;
		this.hierarchy = hierarchy;
		this.commentSupervisor = commentSupervisor;
		this.category = category;
		this.commentL1 = commentL1;
		this.commentL2 = commentL2;
		this.trackValidation = trackValidation;
		this.levelNumber = levelNumber;
		this.commentL3 = commentL3;
		this.commentL4 = commentL4;
		this.commentL5 = commentL5;
		this.trackArray = trackArray;
		Level2 = level2;
		Level3 = level3;
		Level4 = level4;
		Level5 = level5;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getCommentSupervisor() {
		return commentSupervisor;
	}

	public void setCommentSupervisor(String commentSupervisor) {
		this.commentSupervisor = commentSupervisor;
	}

	@Column(name="category")
	public String category ;
	
	@Column(name="commentL1")
	public String commentL1 ;
	

	@Column(name="commentL2")
	public String commentL2 ;
	
	
	@Column(name="trackValidation")
	public Integer trackValidation ;
	
	@Column(name="levelNumber")
	public Integer levelNumber ;
	
	
	public documentValidation(String name, String creationDate, String description, String size, int parentFolderId,
			int Id, String level1, String category, String commentL1, String commentL2, Integer trackValidation,
			Integer levelNumber, String commentL3, String commentL4, String commentL5, int[] trackArray,
			String level2, String level3, String level4, String level5) {
		super(name, creationDate, description, size, parentFolderId, Id);
		Level1 = level1;
		this.category = category;
		this.commentL1 = commentL1;
		this.commentL2 = commentL2;
		this.trackValidation = trackValidation;
		this.levelNumber = levelNumber;
		this.commentL3 = commentL3;
		this.commentL4 = commentL4;
		this.commentL5 = commentL5;
		this.trackArray = trackArray;
		Level2 = level2;
		Level3 = level3;
		Level4 = level4;
		Level5 = level5;
	}

	public Integer getTrackValidation() {
		return trackValidation;
	}

	public void setTrackValidation(Integer trackValidation) {
		this.trackValidation = trackValidation;
	}

	public Integer getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	@Column(name="commentL3")
	public String commentL3 ;
	

	@Column(name="commentL4")
	public String commentL4 ;
	

	@Column(name="commentL5")
	public String commentL5 ;
	

	
	
	
	public documentValidation(String name, String creationDate, String description, String size, int parentFolderId,
			int Id, String level1, String category, String commentL1, String commentL2, String commentL3,
			String commentL4, String commentL5, int[] trackArray, String level2, String level3, String level4,
			String level5) {
		super(name, creationDate, description, size, parentFolderId, Id);
		Level1 = level1;
		this.category = category;
		this.commentL1 = commentL1;
		this.commentL2 = commentL2;
		this.commentL3 = commentL3;
		this.commentL4 = commentL4;
		this.commentL5 = commentL5;
		this.trackArray = trackArray;
		Level2 = level2;
		Level3 = level3;
		Level4 = level4;
		Level5 = level5;
	}

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

	@Column(name="trackArray")
	public int[] trackArray ;
	

	public documentValidation(String name, String creationDate, String description, String size, int parentFolderId,
			int Id, String level1, String category, int[] trackArray, String level2, String level3, String level4,
			String level5) {
		super(name, creationDate, description, size, parentFolderId, Id);
		Level1 = level1;
		this.category = category;
		this.trackArray = trackArray;
		Level2 = level2;
		Level3 = level3;
		Level4 = level4;
		Level5 = level5;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int[] getTrackArray() {
		return trackArray;
	}

	public void setTrackArray(int[] trackArray) {
		this.trackArray = trackArray;
	}

	@Column(name="Level2")
	public String Level2 ;
	

	@Column(name="Level3")
	public String Level3 ;
	

	@Column(name="Level4")
	public String Level4 ;
	

	@Column(name="Level5")
	public String Level5 ;

	public documentValidation(String name, String creationDate, String description, String size, int parentFolderId,
			int Id, String level1, String level2, String level3, String level4, String level5) {
		super(name, creationDate, description, size, parentFolderId, Id);
		Level1 = level1;
		Level2 = level2;
		Level3 = level3;
		Level4 = level4;
		Level5 = level5;
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

	public documentValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public documentValidation(String name, String creationDate, String description, String size, int parentFolderId,
			int Id, String subject, String format, String title, byte[] data, String UpdatedDate) {
		super(name, creationDate, description, size, parentFolderId, Id, subject, format, title, data, UpdatedDate);
		// TODO Auto-generated constructor stub
	}

	public documentValidation(String name, String creationDate, String description, String size, int parentFolderId,
			int Id) {
		super(name, creationDate, description, size, parentFolderId, Id);
		// TODO Auto-generated constructor stub
	}

	
}
