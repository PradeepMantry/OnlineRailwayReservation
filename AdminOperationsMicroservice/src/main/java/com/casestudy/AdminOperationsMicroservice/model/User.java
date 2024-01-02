package com.casestudy.AdminOperationsMicroservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Document(collection="UserDetails")
public class User {

	@Id
	private String id;
	
	@NotEmpty
	@Size(min=3,message="First Name should contain atleast 3 characters")
	private String firstName;
	
	@NotEmpty
	@Size(min=3,message="Last Name should contain atleast 3 characters")
	private String lastName;
	
	@Email(message = "Email address is not valid")
	private String emailId;
	
	@Pattern(regexp = "\\d{10}",message = "Mobile number must contain exactly 10 digits")
	private String mobileNo;
	
	@NotEmpty
	@Size(min=5,message="Username should contain atleast 6 characters")
	private String userName;
	
	@NotEmpty
	@Size(min=6,message="Password should contain atleast 6 characters")
	private String password;
	private String role;
	public User() {
		super();
	}
	public User(String id, String firstName, String lastName, String emailId, String mobileNo, String userName,
			String password, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
