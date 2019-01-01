package com.librarySpring.dao.bookTransaction;

import java.util.ArrayList;

import com.librarySpring.model.Book;
import com.librarySpring.model.BookTransaction;
import com.librarySpring.model.Status;
import com.librarySpring.model.User;



public interface BookTrnasactionDAO {

		public int issueBook(int book_id,int userId);
		
		public Status returnBook(int book_id,int userId);
	    
	    public void deleteBook(int book_id);
	    
	    public User userFined(int userID);
	    
	    public ArrayList<BookTransaction> getAllBookTransaction();
}
