package com.librarySpring.dao.Book;

import java.util.ArrayList;

import com.librarySpring.model.Book;



public interface BookDAO {

	 public void createBook(Book book);
	    
	    public Book getBookByISBN(int id);
	    
	    public void updateBook(Book book);
	    
	    public void deleteBook(int id);
	    
	    public ArrayList<Book> getAllBook();
}
