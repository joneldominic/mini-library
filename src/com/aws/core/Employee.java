/**
 * 
 */
package com.aws.core;

import java.util.ArrayList;

/**
 * @author jonel.tapang
 *
 */
public class Employee {
	
	private static int count;
	private int id;
    private String name;
	private String gender;
	private String address;
	private String contactNum;
	private String position;
	
	private ArrayList<Reference> borrowedReferences = new ArrayList<Reference>();
	private ArrayList<Reference> lostReferences = new ArrayList<Reference>();
	
	
	/* Constructors(Start)
	 */

	public Employee(String name, String gender, String address, 
			String contactNum, String position) {
        
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.contactNum = contactNum;
		this.position = position;
        
        ++count;
        id = count;
    }
	
	/* Constructors(End)
	 */
	
	
	/* Setters(Start)
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String setType(String type) {
		return this.getClass().getSimpleName();
	}
	
	/* Setters(End)
	 */
	
	
	
	/* Getters(Start)
	 */
	
	public int getID() {
		return this.id;
	}
	
	
	public String getName() {
		return this.name;
	}

	
	public String getGender() {
		return this.gender;
	}
	
	
	public String getAddress() {
		return this.address;
	}
	
	
	public String getContactNum() {
		return this.contactNum;
	}
	
	
	public String getPosition() {
		return this.position;
	}
	

	public String getType() {
		return this.getClass().getSimpleName();
	}
	
	
	public double getTotalLostPenalty() {
		double totalPenalties = 0;
		for(Reference lost: this.lostReferences) {
			totalPenalties+=lost.getReplacementAmount();
		}
		
		return totalPenalties;
	}
	
	/* Getters(End)
	 */
	
	

	/* --------------------------------------------------------------------
	 */
	
	public void borrowReference(Reference reference) {
		this.borrowedReferences.add(reference);
	}
	
	
	public void returnReference(int code) {
		 for (int i = 0; i<this.borrowedReferences.size(); i++) {
			 if(borrowedReferences.get(i).getCode() == code) {
				 this.borrowedReferences.remove(i);
			 }	
		 }
	}
	
	
	public void reportLostReference(int code) {
		 for (int i = 0; i<this.borrowedReferences.size(); i++) {
			 if(borrowedReferences.get(i).getCode() == code) {
				 this.lostReferences.add(borrowedReferences.get(i));
				 this.borrowedReferences.remove(i);
			 }	
		 }
	}
	

	public void payLostReference(int code) {
		
	}
	
	
	
	public ArrayList<Reference> getBorrowedReferences() {
		return this.borrowedReferences;        
	}
	
	
	public ArrayList<Reference> getLostReferences() {
		return this.lostReferences;        
	}
	
	public void getLostReferencesByCode(int code) {
		 for (int i = 0; i<this.lostReferences.size(); i++) {
			 if(lostReferences.get(i).getCode() == code) {
				 this.lostReferences.remove(i);
			 }	
		 }   
	}
	
	
	
}
