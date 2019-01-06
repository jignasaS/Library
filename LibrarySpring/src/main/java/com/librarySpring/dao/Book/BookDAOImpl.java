package com.librarySpring.dao.Book;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.librarySpring.dao.bookTransaction.BookTransactionDAOImpl;
import com.librarySpring.model.Book;

@Service
@Transactional(propagation= Propagation.REQUIRED)
public class BookDAOImpl implements BookDAO {

	private static final Logger logger = Logger.getLogger(BookDAOImpl.class);
	
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
		logger.info("<< BookDAOImpl.deleteBook  entry>>");
		try {
			logger.info("book id = "+id);
			Book b=getBookByISBN(id);
			logger.info("book = "+b.getBookname());
			entityManager.remove(b);	
			logger.info("<< BookDAOImpl.deleteBook  exit>>");
		}
		catch(Exception e)
		{
			logger.debug(e.getMessage());
			logger.error(e.getMessage(), e);
			logger.log(Level.INFO, e.getMessage(), e);
		}
	}

	@Override
	public ArrayList<Book> getAllBook() {
		// TODO Auto-generated method stub
		ArrayList<Book> bookList=(ArrayList<Book>) entityManager.createQuery("from Book").getResultList();
		return bookList;
	}

}
