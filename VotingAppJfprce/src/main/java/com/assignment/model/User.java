package com.assignment.model;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique=true, nullable=false, length=255)
	/*
	 * @Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" ,
	 * message="Invalid email address")
	 */
	private String email;
	
	@Column(unique = true)
	private String password;
	
	@Column(unique = true)
	private Long phoneNo;
	
	
}
