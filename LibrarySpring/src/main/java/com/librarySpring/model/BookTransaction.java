package com.librarySpring.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="trnUserBookMap")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@XmlRootElement(name="trnUserBookMap")
public class BookTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8366591438630745589L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private int trnxId;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch=FetchType.LAZY,targetEntity=Book.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="book_id",referencedColumnName="book_id",columnDefinition="int",nullable=false)
	private Book book;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId",nullable=false)
	private User user;
	
	@Column(name="issueStatus")
	private int issueStatus;
	
	
	@Column(name ="issueDate")
	private Date issueDate;
	
	@Column(name ="dueDate")
	private Date dueDate;
	
	@Column(name ="returnDate")
	private Date reutrnDate;
	
	@Column(name ="transaction_Date")
	private Date tranxDate;

	@Column(name ="dueAmount")
	private int dueAmount;
	

	public BookTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookTransaction(Book book, User user, int issueStatus, Date issueDate, Date dueDate, Date reutrnDate,
			Date tranxDate) {
		super();
		this.book = book;
		this.user = user;
		this.issueStatus = issueStatus;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.reutrnDate = reutrnDate;
		this.tranxDate = tranxDate;
	}

	public int getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(int dueAmount) {
		this.dueAmount = dueAmount;
	}
	public BookTransaction(Book book, User user, int issueStatus, Date issueDate, Date dueDate, Date reutrnDate,
			Date tranxDate, int dueAmount) {
		super();
		this.book = book;
		this.user = user;
		this.issueStatus = issueStatus;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.reutrnDate = reutrnDate;
		this.tranxDate = tranxDate;
		this.dueAmount = dueAmount;
	}

	public int getTrnxId() {
		return trnxId;
	}

	public void setTrnxId(int trnxId) {
		this.trnxId = trnxId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(int issueStatus) {
		this.issueStatus = issueStatus;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReutrnDate() {
		return reutrnDate;
	}

	public void setReutrnDate(Date reutrnDate) {
		this.reutrnDate = reutrnDate;
	}

	public Date getTranxDate() {
		return tranxDate;
	}

	public void setTranxDate(Date tranxDate) {
		this.tranxDate = tranxDate;
	}

	@Override
	public String toString() {
		return "BookTransaction [trnxId=" + trnxId + ", book=" + book.getBook_id() + ", user=" + user.getUserId()+ ", issueStatus="
				+ issueStatus + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", reutrnDate=" + reutrnDate
				+ ", tranxDate=" + tranxDate + "]";
	}
	

	

}
