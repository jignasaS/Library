package com.librarySpring.dao.Book;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.librarySpring.model.Book;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class BookDAOImpl implements BookDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void createBook(Book book) {
		// TODO Auto-generated method stub
		entityManager.persist(book);
	}

	@Override
	public Book getBookByISBN(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Book.class,id);
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		entityManager.merge(book);
	}

	@Override
	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		Book b= entityManager.find(Book.class,id);
		entityManager.remove(b);
		
	}

	@Override
	public ArrayList<Book> getAllBook() {
		// TODO Auto-generated method stub
		ArrayList<Book> bookList=(ArrayList<Book>) entityManager.createQuery("from Book").getResultList();
		return bookList;
	}

}
