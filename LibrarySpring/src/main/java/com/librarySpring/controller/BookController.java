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
import com.librarySpring.model.Book;
import com.librarySpring.model.Status;
import com.librarySpring.util.GlobalValue;

@RestController
public class BookController {
	
	private static final Logger logger = Logger.getLogger(BookController.class);

	@Autowired
	private BookDAOImpl bookDAOImpl;
	
	/** Add a new Book 
	 */
	@RequestMapping(value="/createBook",method = RequestMethod.POST,produces="application/json",
			consumes="application/json")
	public @ResponseBody 
	Status addBook(@RequestBody Book book) {
		// TODO Auto-generated method stub
		try
		{
			logger.info("Book adding started");
			bookDAOImpl.createBook(book);
			logger.info("Book added successfully");
			return new Status(1, GlobalValue.bookAddedSuccess);
		}
		catch(Exception e)
		{
			logger.error(e);
			return new Status(0,e.toString());
		}
		
	} 
	
	/*** Retrieve a single Book ***/
	@RequestMapping(value ="book/{id}",produces="application/json",method=RequestMethod.GET)
	  public Book getStudentById(@PathVariable("id") int id)
	    {
	        Book book = bookDAOImpl.getBookByISBN(id);
	        return book;
	    }
	
	/*** Retrieve all Books ***/
	@RequestMapping(value ="books",produces="application/json",method=RequestMethod.GET)
	  public  ArrayList<Book> getAllBooks()
	    {
	        ArrayList<Book> books = bookDAOImpl.getAllBook();
	        return books;
	    }
}
