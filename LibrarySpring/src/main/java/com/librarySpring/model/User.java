package com.librarySpring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@XmlRootElement(name="users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8366591438630745589L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private int userId;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="userAddress")
	private String userAddress;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name ="birthdate")
	private Date bDate;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String userAddress, String gender, Date bDate) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		this.gender = gender;
		this.bDate = bDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName.trim() + ", userAddress=" + userAddress.trim() + ", gender=" + gender + ", bDate=" + bDate
				+ "]";
	}

	
}
