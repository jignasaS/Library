package com.librarySpring.util;

public class GlobalValue {

	public static String bookAddedSuccess = "Book added Successfully";
	public static String userAddedSuccess = "User added Successfully";
	
	public static String bookIssuedSuccess = "Book issued Successfully";
	public static String bookIssuedAlreadyMsg = "Book issued Already";
	public static String bookExceedLimitMsg = "User has exceeded its book issue limit";
	
	public static String bookReturnedSuccess = "Book returned Successfully";
	public static String bookReturnedWithFined = " Book returned after due date so your fined is";
	public static String bookReturnedFailed = "Book returned failed";
	
	public static int bookIssueSuccessState = 1;
	public static int bookIssuedAlready = 2;
	public static int bookIssueLimitExceed =3;
	
	public static int adultBookIssueLimit =2;
	public static int boyBookIssueLimit = 4;
	public  static int girlBookIssueLimit = 6;
	
	public static int finedAmount = 5;
	public static int dueDays = 7;
	
	public static int issued_status =1;
	public static int returned_status=2;
	
	public static int SUCCESS =1;
	public static int FAIL = 0;
	//public static String bookAddedFail = "Book added Successfully";
}
