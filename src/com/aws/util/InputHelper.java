/**
 * 
 */
package com.aws.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author jonel.tapang
 *
 */
public final class InputHelper {
	
	/**
	 * Character Scanner Helper
	 * @return
	 */
	public static char scanChar() {
		
		Scanner inputScanner = new Scanner(System.in);
	    while(true) {
	        try {
	        	Character  input= inputScanner.next().charAt(0);
	            return input.toUpperCase(input);
	        } catch (InputMismatchException ex) {
	            System.out.print("Invalid Input. Please try again: ");
	            inputScanner.nextLine();
	        } 
	    }
	   
    }
	 
	/**
	 * Integer Scanner Helper
	 * @return
	 */
	public static int scanInt() {
		
		Scanner inputScanner = new Scanner(System.in);
	    while(true) {
	        try {
	            return inputScanner.nextInt();
	        } catch (InputMismatchException ex) {
	            System.out.print("Invalid Input. Please try again: ");
	            inputScanner.nextLine();
	        } 
	    }
	   
    }
	
	/**
	 * Integer Scanner with Lower Limit
	 * @param lower
	 * @return
	 */
	public static int scanInt(int lower) {
		
		Scanner inputScanner = new Scanner(System.in);
	    while(true) {
	        try {
	        	int input = inputScanner.nextInt();
	        	if(input >= lower) {
	        		return input;
	        	} else {
	        		System.out.print("Invalid input. Please try again: ");
	        		continue;
	        	}
	            
	        } catch (InputMismatchException ex) {
	            System.out.print("Invalid Input. Please try again: ");
	            inputScanner.nextLine();
	        } 
	    }
	   
    }
	
	
	/**
	 * Integer Scanner Helper with Lower and Upper Limits
	 * @return
	 */
	public static int scanInt(int lower, int upper) {
		
		Scanner inputScanner = new Scanner(System.in);
	    while(true) {
	        try {
	        	int input = inputScanner.nextInt();
	        	if(input >= lower && input <= upper) {
	        		return input;
	        	} else {
	        		System.out.print(String.format("%-5s%-50s", "",">> Warning: Unrecognized input. Please try again: "));
	        		continue;
	        	}
	            
	        } catch (InputMismatchException ex) {
	            System.out.print("Invalid Input. Please try again: ");
	            inputScanner.nextLine();
	        } 
	    }
	   
    }
	
	
	/**
	 * Integer Scanner Helper with Lower and Upper Limits and Message Option
	 * @return
	 */
	public static int scanInt(int lower, int upper, String errMessage) {
		
		Scanner inputScanner = new Scanner(System.in);
	    while(true) {
	        try {
	        	int input = inputScanner.nextInt();
	        	if(input >= lower && input <= upper) {
	        		return input;
	        	} else {
	        		System.out.println(errMessage);
	        		System.out.print("Please try again: ");
	        		continue;
	        	}
	            
	        } catch (InputMismatchException ex) {
	            System.out.print("Invalid Input. Please try again: ");
	            inputScanner.nextLine();
	        } 
	    }
	   
    }
	
	
	/**
	 * Double Scanner Helper
	 * @return
	 */
	public static double scanDouble() {
		
		Scanner inputScanner = new Scanner(System.in);
	    while(true) {
	        try {
	            return inputScanner.nextInt();
	        } catch (InputMismatchException ex) {
	            System.out.print("Invalid Input. Please try again: ");
	            inputScanner.nextLine();
	        } 
	    }
	   
    }
	 
	/**
	 * String Scanner Helper
	 * @return
	 */
	public static String scanString() {
	        
		Scanner inputScanner = new Scanner(System.in);
	    while(true) {
	        try {
	            return inputScanner.nextLine();
	        } catch (InputMismatchException ex) {
	            System.out.print("Invalid Input. Please try again: ");
	            inputScanner.nextLine();
	        }
	    }
    }
	
	/**
	 * String(Date) Scanner Helper
	 * @return
	 */
	public static String scanDate() {
        
		Scanner inputScanner = new Scanner(System.in);
	    while(true) {
	        try {
	        	String input = inputScanner.nextLine();

	        	String delims = "[/]";
	    		String[] tokens = input.split(delims);
	    		int parsedDate[] = new int[tokens.length];
	    		for (int i = 0; i < tokens.length; i++) {
	    			parsedDate[i] = Integer.parseInt(tokens[i]);
	    		}
	    		
	    		if(tokens.length != 3 || (parsedDate[0]>12) || (parsedDate[1]>31 || parsedDate[1]<1 || parsedDate[2]<2019)) {
//	    			 System.out.print("Invalid Input. Please try again: ");
	        		System.out.print(String.format("%-5s%-33s", "",">> Invalid Input. Please try again: "));
	        		

	    		} else {
	    			 return input;
	    		}
	        } catch (InputMismatchException ex) {
	            System.out.print("Invalid Input. Please try again: ");
	            inputScanner.nextLine();
	        }
	    }
    }
	
	/**
	 * Current Day Getter (String)
	 * @return
	 */
	public static String getDateToday() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
		return (String)(dateFormat.format(date));
	}
	
	/**
	 * Borrow Duration Calculator
	 * @param borrowDate
	 * @param borrowDuration
	 * @return
	 */
	public static String getCalculatedBorrowDuration(String borrowDate, int borrowDuration) {
		
		int numOfDayPerMonth[] = {31, 28, 30, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		String returnDate = "";
		
		String delims = "[/]";
		String[] tokens = borrowDate.split(delims);
		int parsedDate[] = new int[tokens.length];
		
		for (int i = 0; i < tokens.length; i++) {
			parsedDate[i] = Integer.parseInt(tokens[i]);
		}
		
		
		int date = parsedDate[1];
		int month = parsedDate[0];
		int year = parsedDate[2];
		
		if((date+borrowDuration) > numOfDayPerMonth[month-1]) {
			date = (date+borrowDuration) - numOfDayPerMonth[month-1];
			month++;
			
			while(date>numOfDayPerMonth[month-1]) {
				date = date - numOfDayPerMonth[month-1];
				month++;
			}
		} else {
			date = (date+borrowDuration);
		}
		
		returnDate = month + "/" + date + "/" +  year;
		
		return returnDate;
	}
	
	
	/**
	 * Calculates number of days
	 * @param dateToReturn
	 * @param dateReturned
	 * @return
	 */
	public static int getOverDueDays(String dateToReturn, String dateReturned) {
		int numOfDayPerMonth[] = {31, 28, 30, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		String delims = "[/]";
		
		String[] tokensA = dateToReturn.split(delims);
		String[] tokensB = dateReturned.split(delims);
		
		int parsedDateToReturn[] = new int[tokensA.length];
		int parsedDateReturned[] = new int[tokensB.length];
		
		for (int i = 0; i < tokensA.length; i++) {
			parsedDateToReturn[i] = Integer.parseInt(tokensA[i]);
		}
		
		for (int i = 0; i < tokensB.length; i++) {
			parsedDateReturned[i] = Integer.parseInt(tokensB[i]);
		}
		
		int month = parsedDateReturned[0] - parsedDateToReturn[0];
		int days = parsedDateReturned[1] - parsedDateToReturn[1];
		int year = parsedDateReturned[2] - parsedDateToReturn[2];
		
		while(year != 0) {
			days += 365;
			--year;
		}
		
		while(month != 0) {
			days += 30;
			--month;
		}
		
		return days;
	}

}
