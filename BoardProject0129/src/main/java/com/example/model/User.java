package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Id")
	private Long Id;
	@Column(name="userId")
	private String userId;
	@Column(name="userPw")
	private String userPw;
	@Column(name="userName")
	private String userName;
	@Column(name="userEmail")
	private String userEmail;
	@Column(name="signUpDate")
	private String signUpDate;
}
