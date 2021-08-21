/**
 * 
 */
package com.aws.core;

import com.aws.core.Reference;

/**
 * @author jonel.tapang
 *
 */
public class FlashCard extends Reference {

	private String creator;
	private int numberOfCard;
	
	
	public FlashCard(String title, String creator, int numberOfCard, int stock, 
			double replacementAmount, double fines, int borrowDurationLimit) {

		super(title, stock, replacementAmount, fines, borrowDurationLimit);
		this.creator = creator;
		this.numberOfCard = numberOfCard;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	public void setNumberOfCard(int numberOfCard) {
		this.numberOfCard = numberOfCard;
	}
	
	
	public String getCreator() {
		return this.creator;
	}
	
	
	public int getNumberOfCard() {
		return this.numberOfCard;
	}
	

}
