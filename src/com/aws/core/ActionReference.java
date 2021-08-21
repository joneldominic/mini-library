/**
 * 
 */
package com.aws.core;

/**
 * @author jonel.tapang
 *
 */
public class ActionReference extends Reference {

	private String author;
	
	
	public ActionReference(String title, String author, int stock, double replacementAmount,
			double fines, int borrowDurationLimit) {
		
		super(title, stock, replacementAmount, fines, borrowDurationLimit);
		this.author = author;
	}
	
	
	public void setAuthor(String author) {
		this.author = author;
	}
	

	public String getAuthor() {
		return this.author;
	}
	

}
