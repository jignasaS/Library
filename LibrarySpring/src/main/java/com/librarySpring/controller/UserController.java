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


import com.librarySpring.dao.User.userDAOImpl;
import com.librarySpring.model.Status;
import com.librarySpring.model.User;
import com.librarySpring.util.GlobalValue;

@RestController
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private userDAOImpl userDAOImpl;
	
	/** Add a new Book 
	 */
	@RequestMapping(value="/createUser",method = RequestMethod.POST,produces="application/json",
			consumes="application/json")
	public @ResponseBody 
	Status addBook(@RequestBody User user) {
		// TODO Auto-generated method stub
		try
		{
			logger.info("USer adding started");
			userDAOImpl.createUser(user);
			logger.info("User added");
			return new Status(1, GlobalValue.userAddedSuccess);
		}
		catch(Exception e)
		{
			logger.error(e);
			return new Status(0,e.toString());
		}
		
	} 
	
	
	/*** Retrieve all Users ***/
	@RequestMapping(value ="users",produces="application/json",method=RequestMethod.GET)
	  public  ArrayList<User> getAllUsers()
	    {
	        ArrayList<User> users = userDAOImpl.getAllUser();
	        return users;
	    }
}
