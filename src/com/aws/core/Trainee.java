/**
 * 
 */
package com.aws.core;

import java.util.ArrayList;

import com.aws.util.InputHelper;

/**
 * @author jonel.tapang
 *
 */
public class Trainee extends Employee {

	private ArrayList<Reference> lateReturnedReferences = new ArrayList<Reference>();
	
	
	public Trainee(String name, String gender, String address,
			String contactNum, String position) {
		
		super(name, gender, address, contactNum, position);
	}
	
	
	public ArrayList<Reference> getLateReturnedReferences() {
		return this.lateReturnedReferences;        
	}
	
	public void payLateReturnedReferences(int code) {
		
	}
	
	public void addLateReturnedReferences(Reference referenece) {
		this.lateReturnedReferences.add(referenece);
	}
	
	public double getTotalLateFines() {
		double totalPenalties = 0;
		for(Reference lost: this.lateReturnedReferences) {
			totalPenalties+=lost.getFines() * InputHelper.getOverDueDays(lost.getDateToReturn(), lost.getDateReturned());
		}
		
		return totalPenalties;
	}
	
}
