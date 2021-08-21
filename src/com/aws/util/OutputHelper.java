/**
 * 
 */
package com.aws.util;

import java.util.ArrayList;

import com.aws.core.ActionReference;
import com.aws.core.Employee;
import com.aws.core.FlashCard;
import com.aws.core.Magazine;
import com.aws.core.OtherMaterial;
import com.aws.core.Reference;
import com.aws.core.VideoCD;

/**
 * @author jonel.tapang
 *
 */
public final class OutputHelper {
	
	public static void header() {
		System.out.println(" __________________________________________________________");
		System.out.println(String.format("%-20s%20s%20s", "|","","|"));
		System.out.println(String.format("%-20s%20s%20s", "|","Mini-Library System","|"));
		System.out.println("|__________________________________________________________|");
	}
	
	public static void mainMenu() {
		header();
		System.out.println(String.format("%-20s%20s%20s", "","",""));
		System.out.println(String.format("%-5s%-20s%10s%20s%5s", "","","Main Menu","",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[1] View References","","[2] Employees Directory",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[3] Manage Library","","[4] Exit",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		System.out.print(String.format("%13s","Choice: "));

	}
	
	public static void referenceMenu() {
		
		System.out.println(String.format("%-5s%-18s%14s%18s%5s", "","","Reference Menu","",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[1] View all References","","[2] View Action Refs.",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[3] View Video CDs","","[4] View Flash Cards",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[5] View Magazines","","[6] View Other Materials",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[7] Search (by Title)","","[8] Back (Main Menu)",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		System.out.print(String.format("%13s","Choice: "));
	}
	
	public static void employeesDirectoryMenu() {
		
		System.out.println(String.format("%-5s%-15s%20s%15s%5s", "","","Employees Directory","",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[1] View all Employees","","[2] View Trainees",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[3] View Trainers","","[4] Search (by Name)",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[5] Back (Main Menu)","","",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		System.out.print(String.format("%13s","Choice: "));
	}
	
	
	public static void dispReferenceAsTable(ArrayList<Reference> references) {
		
		System.out.println(String.format("%-5s%-18s%14s%18s%5s", "","","Reference(s)","",""));
		System.out.println(String.format("%-60s","------------------------------------------------------------"));
		System.out.println(String.format("%-5s%-29s%-6s%-15s", "Code", "Title", "Stock", "Type"));
		System.out.println(String.format("%-60s","------------------------------------------------------------"));

		if(!references.isEmpty()) {
			for (Reference reference : references) {
				System.out.println(String.format("%-5.5s%-29.29s%-6.5s%-15.15s", 
						reference.getCode(), reference.getTitle(), reference.getStock(), reference.getType()));
			}
		} else {
			System.out.println("*** No Reference Found.");
		}
		System.out.println(String.format("%-60s","------------------------------------------------------------"));
	}
	
	
	public static void displaySelectedReference(Reference reference, String title) {
		System.out.println(String.format("%-5s%-16s%18s%16s%5s", "","",title,"",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		
		if(reference != null) {
			
			System.out.println(String.format("%-5s%-23s%-30s%5s", "","Code: ",reference.getCode(),""));
			System.out.println(String.format("%-5s%-23s%-30s%5s", "","Title: ",reference.getTitle(),""));

			switch(reference.getType()) 
	        { 
	            case ReferenceType.ACTION_REFERENCE: 
	    			System.out.println(String.format("%-5s%-23s%-30s%5s", "","Author: ",((ActionReference)reference).getAuthor(),""));
	                break; 
	            case ReferenceType.VIDEO_CD: 
	            	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Director: ",((VideoCD)reference).getDirector(),""));
	            	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Duration: ",((VideoCD)reference).getDuration(),""));
	                break; 
	            case ReferenceType.FLASH_CARD: 
	            	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Creator: ",((FlashCard)reference).getCreator(),""));
	            	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Number of Card: ",((FlashCard)reference).getNumberOfCard(),""));
	                break;
	            case ReferenceType.MAGAZINE: 
	            	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Editor: ",((Magazine)reference).getEditor(),""));
	            	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Volume: ",((Magazine)reference).getVolume(),""));
	                break;
	            case ReferenceType.OTHER_MATERIAL: 
	            	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Creator: ",((OtherMaterial)reference).getCreator(),""));
	            	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Description: ",((OtherMaterial)reference).getDescription(),""));
	                break;
	            default: 
	                System.out.println("***Reference Type not Valid..."); 
	        } 
			
        	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Max Borrow Duration: ",reference.getBorrowDurationLimit() + 
        			(reference.getBorrowDurationLimit() > 1? " Days" : " Day"),""));

        	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Stock: ",reference.getStock(),""));
        	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Type: ",reference.getType(),""));
		} else {
			System.out.println(String.format("%-5s%-55s", "","*** Selected Reference Not Found."));
		}
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));

	}
	
	
	public static void borrowReferenceHandlerMenu() {
		
		System.out.println(String.format("%-5s%-17s%16s%17s%5s", "","","Select Borrower","",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		System.out.println(String.format("%-5s%-25s%2s%-24s%5s", "","[1] View All Employees","","[2] Search Employee (Name)",""));
		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[3] Back (Reference Menu)","","[4] Back (Main Menu)",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		System.out.print(String.format("%13s","Choice: "));
	}
	
	
	public static void displayEmployeesAsTable(ArrayList<Employee> employees) {
		
			System.out.println(String.format("%-5s%-18s%14s%18s%5s", "","","Employee(s)","",""));
			System.out.println(String.format("%-60s","------------------------------------------------------------"));
			System.out.println(String.format("%-4s%-29s%-10s%-15s", "ID", "Name", "Gender", "Type"));
			System.out.println(String.format("%-60s","------------------------------------------------------------"));

			if(!employees.isEmpty()) {
				for (Employee employee : employees) {
					System.out.println(String.format("%-4s%-29s%-10s%-15s", 
							employee.getID(), employee.getName(), employee.getGender(), employee.getType()));
				}
			} else {
				System.out.println("*** No Employee Found.");
			}
    		
			System.out.println(String.format("%-60s","------------------------------------------------------------"));
	}
	
	public static void displaySelectedEmployee(Employee employee) {
		System.out.println(String.format("%-5s%-16s%18s%16s%5s", "","","Selected Employee","",""));
		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
		
    	System.out.println(String.format("%-5s%-23s%-30s%5s", "","ID: ",employee.getID(),""));
    	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Name: ",employee.getName(),""));
    	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Gender: ",employee.getGender(),""));
    	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Address: ",employee.getAddress(),""));
    	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Contact Number: ",employee.getContactNum(),""));
    	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Position: ",employee.getPosition(),""));
    	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Type: ",employee.getType(),""));

		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
	}
	
	
	public static void dispBorrowedReferences(ArrayList<Reference> references) {
		
//		System.out.println("\nBorrowed Reference(s)");
//		System.out.println("-----------------------------------------------------------------");
//		System.out.println("Code\t| Title	| Date Borrowed	| Borrow Duration| Date to Return");
//		System.out.println("-----------------------------------------------------------------");
//		
		
		System.out.println(String.format("%-5s%-14s%18s%14s%5s", "","","Borrowed Reference(s)","",""));
		System.out.println(String.format("%-60s","---------------------------------------------------------------------------------"));
		System.out.println(String.format("%-5s%-30s%-15s%-15s%-15s", "Code", "Title", "Date Borrowed", "Duration", "Date to Return"));
		System.out.println(String.format("%-60s","---------------------------------------------------------------------------------"));

		if(!references.isEmpty()) {
			for (Reference borrowedRef : references) {
				System.out.println(String.format("%-5s%-30s%-15s%-15s%-15s", 
						borrowedRef.getCode(), 
						borrowedRef.getTitle(),
						borrowedRef.getDateBorrowed(), 
						borrowedRef.getBorrowDaysDuration(),
						borrowedRef.getDateToReturn()));
			}
		} else {
			System.out.println("*** No Borrowed Reference Found.");
		}
		System.out.println(String.format("%-60s","---------------------------------------------------------------------------------"));
	}
	
	
	
	
	
	
	
//	
//	public static void asd() {
//		
//		
////		  System.out.format("%32s%10d%16s", "String1", 2, "String2");
//      System.out.println(String.format("|%-10.5s|", "Hello World"));
////      System.out.format("%32s%10d%16s", "String222222222222221", 2, "String2");
//
//	}
	
	
	
	
}
