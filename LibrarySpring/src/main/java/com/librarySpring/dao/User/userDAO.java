package com.librarySpring.dao.User;

import java.util.ArrayList;

import com.librarySpring.model.Book;
import com.librarySpring.model.User;



public interface userDAO {

	 public void createUser(User user);
	    
	 public User getUserById(int userId);
	public ArrayList<User> getAllUser();
	
}
