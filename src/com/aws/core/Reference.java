/**
 * 
 */
package com.aws.core;

/**
 * @author jonel.tapang
 *
 */
public abstract class Reference {

	private static int count;
	private int code;
	private String title; 
	private int stock;
	private double replacementAmount;
	private double fines;
	private int borrowDurationLimit;
	private String dateBorrowed;
	private int borrowDaysDuration;
	private String dateToReturn;
	private String dateReturned;
	
	
	/* Constructors(Start)
	 */
	
	public Reference(String title, int stock, double replacementAmount, 
			double fines, int borrowDurationLimit) {
      
		this.title = title;
		this.stock = stock;
		this.replacementAmount = replacementAmount;
		this.fines = fines;
        this.borrowDurationLimit = borrowDurationLimit;
        
        ++count;
        code = count;
    }
	
	public Reference(int code, String title, int stock, double replacementAmount, 
			double fines, int borrowDurationLimit) {
      
		this.code = code;
		this.title = title;
		this.stock = stock;
		this.replacementAmount = replacementAmount;
		this.fines = fines;
        this.borrowDurationLimit = borrowDurationLimit;
    }

	/* Constructors(End)
	 */
	
	
	/* Setters(Start)
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	public void setReplacementAmount(double replacementAmount) {
		this.replacementAmount = replacementAmount;
	}
	
	
	public void setFines(double fines) {
		this.fines = fines;
	}
	
	
	public void setBorrowDurationLimit(int borrowDurationLimit) {
		this.borrowDurationLimit = borrowDurationLimit;
	}
	
	
	public void setDateBorrowed(String dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}
	
	
	public void setBorrowDaysDuration(int borrowDaysDuration) {
		this.borrowDaysDuration = borrowDaysDuration;
	}
	
	
	public void setDateToReturned(String dateToReturned) {
		this.dateToReturn = dateToReturned;
	}
	
	
	public void setDateReturned(String dateReturned) {
		this.dateReturned = dateReturned;
	}
	

	
	/* Setters(End)
	 */
	
	
		
	/* Getters(Start)
	 */
	
	public int getCode() {
		return this.code;
	}
	
	
	public String getTitle() {
		return this.title;
	}
	
	
	public int getStock() {
		return this.stock;
	}
	
	
	public double getReplacementAmount() {
		return this.replacementAmount;
	}
	
	
	public double getFines() {
		return this.fines;
	}
	
	
	public int getBorrowDurationLimit() {
		return this.borrowDurationLimit;
	}
	
	
	public String getDateBorrowed() {
		return this.dateBorrowed;
	}
	
	
	public int getBorrowDaysDuration() {
		return this.borrowDaysDuration;
	}
	
	
	public String getDateToReturn() {
		return this.dateToReturn;
	}
	
	
	public String getDateReturned() {
		return this.dateReturned;
	}
	
	
	public String getType() {
		return this.getClass().getSimpleName();
	}
	
	
	/* Getters(End)
	 */


	
	/* --------------------------------------------------------------------
	 */
	
	public Reference borrowReference() {
        --this.stock;
        return this;
    }
	
	public void returnReference() {
        ++this.stock;
    }
	
	public Reference borrowReference(String dateBorrowed, int borrowDaysDuration, String dateToReturn) {
		this.dateBorrowed = dateBorrowed;
		this.borrowDaysDuration = borrowDaysDuration;
		this.dateToReturn = dateToReturn;
//		
//        --this.stock;
        return this;
    }
	
	
}
