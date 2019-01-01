package com.librarySpring.dao.User;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.librarySpring.model.Book;
import com.librarySpring.model.User;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class userDAOImpl implements userDAO {

	@PersistenceContext
	private EntityManager entityManager;

	
	public userDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
	}

	@Override
	public ArrayList<User> getAllUser() {
		// TODO Auto-generated method stub
		ArrayList<User> userList=(ArrayList<User>) entityManager.createQuery("from User").getResultList();
		return userList;
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class,userId);
	}

}
