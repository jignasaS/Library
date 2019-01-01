package com.librarySpring.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;

import org.apache.log4j.Logger;

import com.librarySpring.dao.bookTransaction.BookTransactionDAOImpl;

public class DateCalc {
	private static final Logger logger = Logger.getLogger(DateCalc.class);
   //Convert java date into sql date
	public java.sql.Date convertToDate(java.util.Date date) {
		
		java.sql.Date currentDate = new java.sql.Date(date.getTime());
		return currentDate;
	}
	
	    //Convert java date into sql date using localDate
		public Date calDueDate(java.util.Date currDate) {
			// TODO Auto-generated method stub
			//convert java.util to localDate
			LocalDate today = currDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate localDate = today.plusDays(GlobalValue.dueDays);
			
			//Convert local date to SQL date
	        java.sql.Date dueDate = java.sql.Date.valueOf( localDate );
			return dueDate;
		}
		
		//Calculate Age from birthdate
		public int calAge(java.util.Date birthDate)
		{
			logger.info(" << DateCalc.calAge entry >>");
			
			try
			{
			logger.info(" birthdate = "+birthDate);
			LocalDate today = LocalDate.now();      
			logger.info(" today = "+today);
			
			LocalDate birthD = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			logger.info(" birthdate = "+birthD);
			Period p = Period.between(birthD, today);
			logger.info(" age : "+p.getYears());
			logger.info(" << DateCalc.calAge exit >>");
		    return p.getYears();   
			}
			catch(Exception e)
			{
				e.getStackTrace();
				logger.debug(e.getMessage());
				logger.error(e);
				return 0;
			}
		}
		
		//Calculate fined
		public int calFined(java.sql.Date dueDate)
		{
			logger.info(" << DateCalc.calFined entry >>");
			
			try
			{
			
			LocalDate today = LocalDate.now();      
			logger.info(" today = "+today);
			logger.info(" duedate = "+dueDate);
			
			LocalDate dueD = dueDate.toLocalDate(); // sql date to local date
			logger.info(" duedate = "+dueD);
			Period p = Period.between(dueD, today);
			logger.info(" due days : "+p.getDays());
			
			int days = p.getDays();
			
			if(days > 0)
			{
				int finedAmount = days * GlobalValue.finedAmount;
				logger.info(" Amount : "+ finedAmount);
				logger.info(" << DateCalc.calFined exit >>");
				return finedAmount;
			}
			else
			{
				logger.info(" << DateCalc.calFined exit >>");
				return 0;
			}
			}
			catch(Exception e)
			{
				e.getStackTrace();
				logger.debug(e.getMessage());
				logger.error(e);
				return -1;
			}
		}
}
