package com.librarySpring.model;

import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="books")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@XmlRootElement(name="books")
public class Book implements Serializable{

	private static final long serialVersionUID = -6580253385322647636L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private int book_id;
	
	@Column(name="title")
    private String bookname;
	
	@Column(name="authorName")
    private String authName;
	
	

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Book(String bookname, String authName) {
		super();
		this.bookname = bookname;
		this.authName = authName;
	}




	public int getBook_id() {
		return book_id;
	}


	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}


	public String getBookname() {
		return bookname;
	}


	public void setBookname(String bookname) {
		this.bookname = bookname;
	}


	public String getAuthName() {
		return authName;
	}


	public void setAuthName(String authName) {
		this.authName = authName;
	}


	@Override
	public String toString() {
		return "Book [bookname=" + bookname.trim() + ", authName=" + authName.trim() + "]";
	}

	
}
