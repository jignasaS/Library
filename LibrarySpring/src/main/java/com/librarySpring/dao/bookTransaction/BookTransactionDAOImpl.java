package com.librarySpring.dao.bookTransaction;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.usertype.LoggableUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.librarySpring.controller.TransactionController;
import com.librarySpring.dao.Book.BookDAOImpl;
import com.librarySpring.dao.User.userDAOImpl;
import com.librarySpring.model.Book;
import com.librarySpring.model.BookTransaction;
import com.librarySpring.model.Status;
import com.librarySpring.model.User;
import com.librarySpring.util.DateCalc;
import com.librarySpring.util.GlobalValue;


@Service
@Transactional(propagation= Propagation.REQUIRED)
public class BookTransactionDAOImpl implements BookTrnasactionDAO {

	private static final Logger logger = Logger.getLogger(BookTransactionDAOImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private userDAOImpl userDAOImpl;
	
	private DateCalc dateCalc=  new DateCalc();

	@Override
	public int issueBook(int book_id, int userId) {
		// TODO Auto-generated method stub
		logger.info(" << BookTransactionDAOImpl.issueBook entry >>");
		int isValidForBookISsue = isBookUserValid(book_id,userId);
		
		if(isValidForBookISsue == GlobalValue.bookIssueSuccessState)
		{
			BookTransaction bookTransaction = new BookTransaction();
	
			User tempUser = new User();
			Book tempBook = new Book();
			
			tempUser.setUserId(userId);
			tempBook.setBook_id(book_id);
			
			bookTransaction.setUser(tempUser);
			bookTransaction.setBook(tempBook);
			
			java.util.Date currDate = new java.util.Date();
			java.sql.Date currentDate =dateCalc.convertToDate(currDate);
			bookTransaction.setIssueDate(currentDate);
			bookTransaction.setTranxDate(currentDate);
			
			bookTransaction.setIssueStatus(GlobalValue.issued_status);
			
			java.sql.Date dueDate = dateCalc.calDueDate(currDate);
			bookTransaction.setDueDate(dueDate);
			entityManager.persist(bookTransaction);
			logger.info(" << BookTransactionDAOImpl.issueBook exit >>");
			return GlobalValue.bookIssueSuccessState;
		}
		else if(isValidForBookISsue == GlobalValue.bookIssuedAlready) 
		{
			logger.info(" << BookTransactionDAOImpl.issueBook exit >>");
			return GlobalValue.bookIssuedAlready;
		}
		else {
			logger.info(" << BookTransactionDAOImpl.issueBook exit >>");
			return GlobalValue.bookIssueLimitExceed;
		}
		
	}

	/**
	 * 1. Book already issued cannot be issued again
	 * 2.User cannot have more than 2 books,if he is adult,for kids(boys) 4 books and for girls its 6
	 * @param book_id
	 * @param userId
	 * @return
	 */
	private int isBookUserValid(int book_id, int userId) {
		// TODO Auto-generated method stub
		logger.info(" << BookTransactionDAOImpl.isBookUserValid entry >>");
		ArrayList<BookTransaction> bookTranxList = getBookAllTransaction(book_id,userId);
		
	//	User user=new userDAOImpl().getUserById(userId);
		User user = userDAOImpl.getUserById(userId);
		int age = new DateCalc().calAge(user.getbDate());
	    logger.info("list size:"+bookTranxList.size());
		int bookCount = 1;
		for(BookTransaction b:bookTranxList)
		{
			logger.debug("status::"+b.getBook().getBook_id());
			//check issue status
			if(b.getBook().getBook_id() == book_id && b.getIssueStatus() == GlobalValue.issued_status)
			{	
				logger.debug(b.getBook().getBook_id()+" status "+b.getIssueStatus());
				logger.info(" << BookTransactionDAOImpl.isBookUserValid exit >>");
				return GlobalValue.bookIssuedAlready;
			}
			else if(b.getUser().getUserId() == userId && b.getIssueStatus() == GlobalValue.issued_status)
			{
				if((age > 18 && bookCount == GlobalValue.adultBookIssueLimit) ||
						(age < 18 && user.getGender().equalsIgnoreCase("M")&& bookCount == GlobalValue.boyBookIssueLimit)||
						(age < 18 && user.getGender().equalsIgnoreCase("F")&& bookCount == GlobalValue.girlBookIssueLimit))
				{
					logger.info(" << BookTransactionDAOImpl.isBookUserValid exit >>");
					return GlobalValue.bookIssueLimitExceed;
				}
					
				bookCount++;
			}
		}
		logger.info(" << BookTransactionDAOImpl.isBookUserValid exit >>");
		return GlobalValue.bookIssueSuccessState;
	}

	public ArrayList<BookTransaction> getBookAllTransaction(int book_id,int userId) {
		// TODO Auto-generated method stub
		logger.info(" << BookTransactionDAOImpl.getBookAllTransaction entry >>");
		ArrayList<BookTransaction> bookTranxList=(ArrayList<BookTransaction>) entityManager.createQuery("from BookTransaction bt  where bt.book.book_id = :book_id OR bt.user.userId = :userId").setParameter("book_id",book_id).setParameter("userId",userId).getResultList();
		logger.info(" << BookTransactionDAOImpl.getBookAllTransaction exit >>");
		return bookTranxList;
	}
	
	/**
	 * get book transaction detail from its id and status
	 * @param book_id
	 * @return
	 */
	public ArrayList<BookTransaction> getBookAllByIdWithStatus(int book_id,int issueStatus) {
		// TODO Auto-generated method stub
		try {
			logger.info(" << BookTransactionDAOImpl.getBookAllById entry >>");
			ArrayList<BookTransaction> bookTranxList=(ArrayList<BookTransaction>) entityManager.createQuery("from BookTransaction bt  where bt.book.book_id = :book_id AND bt.issueStatus = :issueStatus ").setParameter("book_id",book_id).setParameter("issueStatus", issueStatus).getResultList();
			logger.info(" << BookTransactionDAOImpl.getBookAllById exit >>");
			return bookTranxList;
		}
		catch(Exception e)
		{
			logger.debug(e.getMessage());
			logger.error(e);
			return null;
		}
		
	}
	
	public ArrayList<BookTransaction> getBookAllById(int book_id) {
		// TODO Auto-generated method stub
		try {
			logger.info(" << BookTransactionDAOImpl.getBookAllById entry >>");
			ArrayList<BookTransaction> bookTranxList=(ArrayList<BookTransaction>) entityManager.createQuery("from BookTransaction bt  where bt.book.book_id = :book_id ").setParameter("book_id",book_id).getResultList();
			logger.info(" << BookTransactionDAOImpl.getBookAllById exit >>");
			return bookTranxList;
		}
		catch(Exception e)
		{
			logger.debug(e.getMessage());
			logger.error(e);
			return null;
		}
		
	}
	
	public ArrayList<BookTransaction> getBookAllTransactionForUser(int book_id,int userId) {
		// TODO Auto-generated method stub
		logger.info(" << BookTransactionDAOImpl.getBookAllTransactionForUser entry >>");
		ArrayList<BookTransaction> bookTranxList=(ArrayList<BookTransaction>) entityManager.createQuery("from BookTransaction bt  where bt.book.book_id = :book_id AND bt.user.userId = :userId").setParameter("book_id",book_id).setParameter("userId",userId).getResultList();
		logger.info(" << BookTransactionDAOImpl.getBookAllTransactionForUser exit >>");
		return bookTranxList;
	}
	@Override
	public Status returnBook(int bookId,int userId) {//update record
		// TODO Auto-generated method stub
		logger.info(" << BookTransactionDAOImpl.returnBook entry >>");
		ArrayList<BookTransaction> bookTranxList = getBookAllTransactionForUser(bookId,userId);
		Status updateStatus = updateBookTransaction(bookTranxList);
		logger.info(" << BookTransactionDAOImpl.returnBook exit >>");
		return updateStatus;
	}

	

	private Status updateBookTransaction(ArrayList<BookTransaction> bookTranxList) {
		// TODO Auto-generated method stub
		Status resultStatus = new Status();
		try
		{
			for(BookTransaction b:bookTranxList)
			{
				logger.debug("status::"+b.getBook().getBook_id());
				if(b.getIssueStatus() == GlobalValue.issued_status)
				{	
					b.setIssueStatus(GlobalValue.returned_status);
					int finedAmount = calculateFinedAmount(b);
					b.setDueAmount(finedAmount);
					//set date
					java.util.Date currDate = new java.util.Date();
					java.sql.Date currentDate =dateCalc.convertToDate(currDate);
					b.setReutrnDate(currentDate);
					b.setTranxDate(currentDate);
					
					
					if(finedAmount == 0 )
					{
						resultStatus.setMessage(GlobalValue.bookReturnedSuccess);
						resultStatus.setCode(GlobalValue.SUCCESS);

						entityManager.merge(b);
						
					}	
					else if(finedAmount > 0)
					{	
						resultStatus.setMessage(GlobalValue.bookReturnedWithFined + finedAmount);
						resultStatus.setCode(GlobalValue.SUCCESS);

						entityManager.merge(b);
						
					}	
					else {
						resultStatus.setCode(GlobalValue.FAIL);
						resultStatus.setMessage(GlobalValue.bookReturnedFailed);
					}
					
					return resultStatus;
				}
			}	
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			logger.debug(e.getMessage());
			resultStatus.setMessage(e.getMessage());
		}
		resultStatus.setCode(GlobalValue.FAIL);
		
		return resultStatus;
	}


	/**
	 * After due date ,fined amount per day is 5 rs/-
	 * @param b
	 * @return
	 */
	private int calculateFinedAmount(BookTransaction b) {
		// TODO Auto-generated method stub
		
		int result = new DateCalc().calFined(b.getDueDate());
		return result;
	}


	@Override
	public int deleteBook(int book_id) {
		// TODO Auto-generated method stub
		logger.info(" << BookTransactionDAOImpl.deleteBook entry >>");
		ArrayList<BookTransaction> bookTranxList = getBookAllByIdWithStatus(book_id,GlobalValue.issued_status);
		
		//Check whether book is issued already(Can't delete that book)
		if(bookTranxList == null || bookTranxList.size() == 0)
		{
		    int result = deleteBookOp(book_id);
		    if(result == GlobalValue.SUCCESS)
			{
		    	logger.info(" << BookTransactionDAOImpl.deleteBook exit >>");
		    	return GlobalValue.bookDeletedSuccessfully;
			}
		    else
		    {
		    	logger.info("ERROR");
				logger.info(" << BookTransactionDAOImpl.deleteBook exit >>");
				return GlobalValue.FAIL;
		    }
			
		}
		else
		{
			logger.info(GlobalValue.bookIssuedAlreadyMsg);
			logger.info(" << BookTransactionDAOImpl.deleteBook exit >>");
			return GlobalValue.bookDeletedFailed;
		}
	}

	private int deleteBookOp(int book_id) {
		// TODO Auto-generated method stub
		
			logger.info("<< BookTransactionDAOImpl.deleteBookOp  entry>>");
			try {
				logger.info("book id = "+book_id);
				Book b=entityManager.find(Book.class,book_id);
				ArrayList<BookTransaction> bookTranxList = getBookAllById(book_id);
				
				b.setBookTransactions(bookTranxList);
				logger.info("book = "+b.getBookname());
				entityManager.remove(b);
				logger.info("<< BookTransactionDAOImpl.deleteBookOp  exit>>");
				return GlobalValue.SUCCESS;
			}
			catch(Exception e)
			{
				logger.debug(e.getMessage());
				logger.error(e.getMessage(), e);
				logger.log(Level.INFO, e.getMessage(), e);
				return GlobalValue.FAIL;
			}
	}

}
