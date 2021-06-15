package com.example.demo.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Query")
public class user {
	
	



	@Id
	@Column(name="UTKTCODE")
	public String UTKTCODE;
	

	@Column(name="UTCTNOM")
	public String UTCTNOM;

	

	@Column(name="UTCTSUPER")
	public String UTCTSUPER;
	
}
