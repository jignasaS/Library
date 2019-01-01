package com.librarySpring.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarySpring.dao.Book.BookDAOImpl;
import com.librarySpring.dao.bookTransaction.BookTransactionDAOImpl;
import com.librarySpring.model.Book;
import com.librarySpring.model.BookTransaction;
import com.librarySpring.model.BookUser;
import com.librarySpring.model.Status;
import com.librarySpring.util.GlobalValue;

@RestController
public class TransactionController {
	
	private static final Logger logger = Logger.getLogger(TransactionController.class);

	@Autowired
	private BookTransactionDAOImpl bookTransactionDAOImpl;
	
	/** Issue a new Book 
	 */
	@RequestMapping(value="/issueBook",method = RequestMethod.POST,produces="application/json",
			consumes="application/json")
	public @ResponseBody 
	Status bookIssue(@RequestBody BookUser bookUser) {
		// TODO Auto-generated method stub
		try
		{
			logger.info("Book issuing started");
			int resultState = bookTransactionDAOImpl.issueBook(bookUser.getBook_id(), bookUser.getUser_id());
			logger.info("Book issued successfully");
			
			if(resultState == GlobalValue.bookIssueSuccessState)
			return new Status(GlobalValue.bookIssueSuccessState, GlobalValue.bookIssuedSuccess);
			else if (resultState == GlobalValue.bookIssuedAlready)
			return new Status(GlobalValue.bookIssuedAlready, GlobalValue.bookIssuedAlreadyMsg);
			else
			return new Status(GlobalValue.bookIssueLimitExceed, GlobalValue.bookExceedLimitMsg);
		}
		catch(Exception e)
		{
			logger.error(e);
			return new Status(0,e.toString());
		}
		
	} 
	
	/*** Return a Book ***/
	@RequestMapping(value="/returnBook",method = RequestMethod.POST,produces="application/json",
			consumes="application/json")
	public @ResponseBody 
	Status returnBook(@RequestBody BookUser bookUser) {
			//@PathVariable("id") int bookId) {
		try
		{
			logger.info("Book returning started");
			//int finedStatus = bookTransactionDAOImpl.returnBook(bookUser.getBook_id(),bookUser.getUser_id());
			Status finedStatus = bookTransactionDAOImpl.returnBook(bookUser.getBook_id(),bookUser.getUser_id());
			logger.info("Book returned successfully");
			return finedStatus;
		/*	if(finedAmount == 0 )
			return new Status(1, GlobalValue.bookIssuedSuccess);
			else
				return new Status(1, GlobalValue.bookReturnedWithFined + finedAmount);	*/
		}
		catch(Exception e)
		{
			logger.error(e);
			return new Status(0,e.toString());
		}
		
	}
	/*@RequestMapping(value ="book/{id}",produces="application/json",method=RequestMethod.GET)
	  public Book getStudentById(@PathVariable("id") int id)
	    {
	        Book book = bookDAOImpl.getBookByISBN(id);
	        return book;
	    }
	*/
	/*** Retrieve all Book transaction ***/
	@RequestMapping(value ="bookstransaction",produces="application/json",method=RequestMethod.GET)
	  public  ArrayList<BookTransaction> getAllBooksTransactions()
	    {
	        ArrayList<BookTransaction> booksTransaction = bookTransactionDAOImpl.getAllBookTransaction();
	        return booksTransaction;
	    }
}
