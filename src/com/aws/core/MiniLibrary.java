/**
 * 
 */
package com.aws.core;

import java.util.ArrayList;

import com.aws.util.BorrowDurationLimit;
import com.aws.util.EmployeeType;
import com.aws.util.FinesAmount;
import com.aws.util.InputHelper;
import com.aws.util.LostPenalty;
import com.aws.util.OutputHelper;
import com.aws.util.ReferenceType;
import com.aws.util.Setting;

/**
 * @author jonel.tapang
 *
 */
public class MiniLibrary {
	
	private Database database = new Database();
	 
	/**
	 * Entry point of MiniLibrary
	 */
	public void open() {
	        
		// Sample data
		Employee e1 = new Employee("Jonel Dominic Tapang", "Male", "B", "00", "Dev A");
		Employee e2 = new Trainee("Kenshushee Dominic", "Male", "A", "00", "Action");
		Employee e3 = new Trainee("Keenot", "Male", "Sample", "00", "Action");
		Employee e4 = new Trainee("Zoet Tapang", "Female", "C", "00", "Action");
		Employee e5 = new Trainer("Rye Rod", "Male", "Cebu", "00", "TM");
		Employee e6 = new Trainer("Marycelle", "Female", "Cebu", "00", "Asst. TM");
		Employee e7 = new Trainer("Anji", "FeMale", "Cebu", "00", "HR");
		
		Reference a1 = new ActionReference("Action New ", "Keen", 5,
				LostPenalty.ACTION_REFERENCES, FinesAmount.ACTION_REFERENCES, BorrowDurationLimit.ACTION_REFERENCES);
		Reference a2 = new VideoCD("CD A", "BB", 60, 3, 
				LostPenalty.VIDEO_CDs, FinesAmount.VIDEO_CDs, BorrowDurationLimit.VIDEO_CDs);
		Reference a3 = new FlashCard("FlashCard X", "ADDAA", 13, 3,
				LostPenalty.FLASH_CARDS, FinesAmount.FLASH_CARDS, 100);
		Reference a4 = new Magazine("sad New ", "sssss", 5, BorrowDurationLimit.FLASH_CARDS,
				LostPenalty.MAGAZINES, FinesAmount.FLASH_CARDS, BorrowDurationLimit.MAGAZINES);
		Reference a5 = new OtherMaterial("sfdh other mat A", "Ako", "Short Description", 1, 
				LostPenalty.OTHER_MATERIALS, FinesAmount.OTHER_MATERIALS, BorrowDurationLimit.OTHER_MATERIALS);
		
		database.addEmployee(e1);
		database.addEmployee(e2);
		database.addEmployee(e3);
		database.addEmployee(e4);
		database.addEmployee(e5);
		database.addEmployee(e6);
		database.addEmployee(e7);
		
		database.addReference(a1);
		database.addReference(a2);
		database.addReference(a3);
		database.addReference(a4);
		database.addReference(a5);
		
		database.getEmployeeByID(1).borrowReference(a1.borrowReference( "9/27/2019", 3, "9/30/2019"));
		database.getEmployeeByID(1).borrowReference(a2.borrowReference( "9/27/2019", 2, "9/29/2019"));
		database.getEmployeeByID(1).borrowReference(a4.borrowReference( "9/27/2019", 1, "9/28/2019"));
		// ---------------------------------------------------------------------------------------------
		
		
		// Call main menu view of mini library
		mainMenuView();
	}
	
	
	/**
	 * Class contructor with database class as a parameter
	 * @param database
	 */
	public void open(Database database) {
		this.database = database;
		mainMenuView();
	}
	
	
	/**
	 * Main Menu of the Mini-Library System (Starting Point) 
	 */
	private void mainMenuView() {

		OutputHelper.mainMenu();
        int inputMainMenu = InputHelper.scanInt(1,4);
        switch(inputMainMenu) {
            case 1:
            	// Redirect to Reference Menu
            	System.out.println("\n");
                getReferencesMenu();
                break;
            case 2:
            	// Redirect to Employee Directory Menu
            	System.out.println("\n");
                viewEmployeesDirectoryMenu();
                break;
            case 3:
            	// Redirect to Employee Directory Menu
            	System.out.println("\n");
                manageLibraryMenu(false);
                break;
            case 4:
            	// Terminate the program
                System.exit(0);
                break;
            default:
                System.out.println("\n");
        		System.out.println(String.format("%-5s%-50s%5s", "","*** Warning: Unrecognized choice. Please try again. ***",""));
                open();
        }
    }
	
	
	/**
	 * Reference Menu View
	 */
	private void getReferencesMenu() {

		OutputHelper.referenceMenu();
        int inputreferencesMenu = InputHelper.scanInt();
        
        boolean resultNotEmpty = false;
        
        switch(inputreferencesMenu) {
        	case 1: case 2: case 3: case 4: case 5: case 6: case 7:
        		
        		// View references according to choice
        		ArrayList<Reference> queryResult = getReferences(inputreferencesMenu);
        		System.out.println("\n");
        		OutputHelper.dispReferenceAsTable(queryResult);
        		if(!queryResult.isEmpty()) {
        			resultNotEmpty = true;
        		}
        		
        		/* Ask user whether to select a reference or
                 * go back to reference menu or main menu
                 */
                if(resultNotEmpty) { System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[1] Select Reference","","","")); };
        		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[2] Back (Reference Menu))","","[3] Back (Main Menu)",""));
        		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
        		System.out.print(String.format("%13s","Choice: "));
                int intputViewReference = InputHelper.scanInt(1,8);
        	        
                switch(intputViewReference) {
                    case 1:
                    	if(resultNotEmpty) {
                    		System.out.print(String.format("%27s","Enter Reference Code: "));
                    		int selectedCode = InputHelper.scanInt();
                    		Reference selectedReference = this.database.getReferenceByCode(selectedCode);
                    		
                    		// Display Selected Reference
                    		System.out.println();
                    		OutputHelper.displaySelectedReference(selectedReference, "Selected Reference");
                    		
                     		if(selectedReference != null) {

                     			boolean hasStock = selectedReference.getStock() != 0? true:false; 
                     			String warnMessage = "";
                     			if(!hasStock) {
                     				warnMessage = "** Out of Stock ** ";
                     			}
                     			
                     			System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[1] Borrow " + warnMessage,"","",""));
                        		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[2] Back (Reference Menu))","","[3] Back (Main Menu)",""));
                        		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
                        		System.out.print(String.format("%13s","Choice: "));
                        		int referenceSelectAction = InputHelper.scanInt(1,3);

                        		switch(referenceSelectAction) {
                     				case 1:
                     					if(hasStock) {
                         					borrowReferenceHandler(selectedCode);
                     					} else {
                                    		System.out.println(String.format("%-5s%-50s%5s", "","*** Can't borrow reference. It is out of stock ***",""));
                                    		System.out.println();
                     						getReferencesMenu();
                     					}
                     					break;
                     				case 2:
                     					System.out.println("\n");
                     	                getReferencesMenu();
                     					break;
                     				case 3:
                     					System.out.println("\n");
                     	            	mainMenuView();
                     					break;
                     			}
                     		} else {
                     			System.out.println("\n");
             	                getReferencesMenu();
                     		}
                    	} else {
                    		 System.out.println();
                    		 System.out.println(String.format("%-5s%-50s%5s", "","*** Warning: Unrecognized choice... ***",""));
//                             System.out.println("Warning: Unrecognized choice...");
                             System.out.println();
                             getReferencesMenu();
                    	}
                        break;
                    case 2:
                    	System.out.println("\n");
                        getReferencesMenu();
                        break;
                    case 3:
                    	System.out.println("\n");
                    	mainMenuView();
                        break;
                    default:
                        System.out.println("\n");
                 		System.out.println(String.format("%-5s%-50s%5s", "","*** Warning: Unrecognized choice... ***",""));
//                        System.out.println("Warning: Unrecognized choice...");
                        getReferencesMenu();
                }
        		break;
            case 8:
            	System.out.println("\n");
            	mainMenuView();
                break;
            default:
            	System.out.println("\n");
            	System.out.println(">> Warning: Unrecognized choice. Please try again.");
            	getReferencesMenu();
        }
    }
	
	
	/**
	 * Handle Borrowing of Materials
	 * @param selectedCode
	 */
	private void borrowReferenceHandler(int selectedCode) {
		
		OutputHelper.borrowReferenceHandlerMenu();
        int inputBorrowRefHandler = InputHelper.scanInt(1,4);
        int employeeID = -1;

        switch(inputBorrowRefHandler) {
	        case 1: case 2:
	        	
	        	ArrayList<Employee> queryResult = getEmployees(inputBorrowRefHandler==2?4:inputBorrowRefHandler);
	        	OutputHelper.displayEmployeesAsTable(queryResult);
	        	
	        	if(!queryResult.isEmpty()) {
	        		System.out.println();
             		System.out.print(String.format("%27s","Select Borrower (ID): "));

             		employeeID = InputHelper.scanInt();
             		Employee selectedEmployee = this.database.getEmployeeByID(employeeID); 
	        	        
        	        if(selectedEmployee != null) {
        	        	String dateToday = InputHelper.getDateToday();
        	        	
        	    		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
        	    		System.out.println(String.format("%-5s%-50s%5s", "","Borrower: " + selectedEmployee.getName() + " (" + selectedEmployee.getType(),""));

        	        	Reference borrowedRef = this.database.getReferenceByCode(selectedCode);
        	        	System.out.println();
        	        	OutputHelper.displaySelectedReference(borrowedRef, "Reference to Borrow");

        	        	System.out.println(String.format("%5s%-27s","","Borrow Date: " + dateToday));
        	        	System.out.print(String.format("%22s","Borrow Duration: "));
        	        	int maxBorrowDuration = borrowedRef.getBorrowDurationLimit();
        	        	int borrowDuration = InputHelper.scanInt(1,maxBorrowDuration,
        	        			"Warning: Borrow duration must not exceed " + maxBorrowDuration + " Days for this Reference." );
        	        	
        	        	String returnDate = InputHelper.getCalculatedBorrowDuration(dateToday, borrowDuration);
        	        	System.out.println(String.format("%5s%-27s","","Date to be Returned: " + returnDate));
        	    		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
        	        	System.out.print(String.format("%5s%-27s","","[1] Confirm [2] Cancel: "));
        	    		int confirmation = InputHelper.scanInt(1,2);

        	    		
        	    		switch(confirmation) {
        	    			case 1: 
        	    				// Update database record
        	    				this.database.getReferenceByCode(selectedCode).borrowReference();
        	    				
        	    				// Update Borrowed Reference
        	    				borrowedRef.borrowReference(dateToday, borrowDuration, returnDate);
        	    				
        	    				selectedEmployee.borrowReference(borrowedRef);
        	    				
        	    				System.out.println();
                        		System.out.println(String.format("%-5s%-50s%5s", "","*** Borrow Record Added Successfully ***",""));
        	    				System.out.println();
        	    				
        	    				getReferencesMenu();
        	    				break;
        	    			case 2:
        	    				System.out.println();
                        		System.out.println(String.format("%-5s%-50s%5s", "","*** BorrowProcess Cancelled ***",""));
        	    				System.out.println();

        	    				getReferencesMenu();
        	    				break;
        	    		}
        	        	
        	        } else {
        	        	System.out.println();
                		System.out.println(String.format("%-5s%-50s%5s", "","*** Warning: Selected Employee not found ***",""));
                		System.out.println();
                		
        	        	borrowReferenceHandler(selectedCode);
        	        }
	        	}

	            break;
	        case 3:
	        	System.out.println("\n");
                getReferencesMenu();
                break;
	        case 4:
	        	System.out.println("\n");
            	mainMenuView();
	            break;
	        default:
	            System.out.println("\n");
	            System.out.println(">> Warning: Unrecognized choice. ");
	            borrowReferenceHandler(selectedCode);
	    }
        
       
	}
	
	
	/**
	 * Employee Directory Menu
	 */
	private void viewEmployeesDirectoryMenu() {
		
		OutputHelper.employeesDirectoryMenu();
        int inputEmployeeMenu = InputHelper.scanInt();
        
        boolean resultNotEmpty = false;
        
        switch(inputEmployeeMenu) {
        	case 1: case 2: case 3: case 4:
        		/* View Employees according to choice,
        		 */
        		ArrayList<Employee>queryResult = getEmployees(inputEmployeeMenu);
        		System.out.println();
	        	OutputHelper.displayEmployeesAsTable(queryResult);

	        	if(!queryResult.isEmpty()) {
        			resultNotEmpty = true;
        		}
	        	
        		/* Ask user whether to select an employee or
        		 * go back to employee directory menu or main menu
        		 */
	        	if(resultNotEmpty) { System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[1] Select Employee","","","")); };
        		System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[2] Back (Employee Menu)","","[3] Back (Main Menu)",""));
        		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
        		System.out.print(String.format("%13s","Choice: "));
        	
                int inputViewEmployee = InputHelper.scanInt();
        	        
                switch(inputViewEmployee) {
                    case 1:
                    	if(resultNotEmpty) {
//                    		 System.out.print("Enter Employee ID: ");
                    		 System.out.print(String.format("%24s","Enter Employee ID: "));
                    		 int inputEmpID = InputHelper.scanInt();
                    		 Employee selectedEmployee = this.database.getEmployeeByID(inputEmpID); 
                             if(selectedEmployee != null) {
                            	 System.out.println();
                            	 OutputHelper.displaySelectedEmployee(selectedEmployee);

                            	 System.out.println(String.format("%-5s%-28s%2s%-24s%5s", "","[1] View Borrowed Refs.","","[2] View Lost Refs.",""));
                            	 System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[3] View Late Returned Refs.","","[4] Back (Employee Menu)",""));
                            	 System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[5] Back (Main Menu)","","",""));
                            	 System.out.print(String.format("%13s","Choice: "));
                                 int inputViewEmployeeVBM = InputHelper.scanInt(1,5);
                                 
                                 switch(inputViewEmployeeVBM) {
                                 	case 1:
                                 		getBorrowedReferences(selectedEmployee);
                                 		break;
                                 	case 2:
                                 		 getLostReferences(selectedEmployee);
                                 		break;
                                 	case 3:
                                 		 getLateReturnedReferences(selectedEmployee);
                                 		System.out.println();
                                 		viewEmployeesDirectoryMenu();
                                 		break;
                                 	case 4:
                                 		System.out.println();
                                 		viewEmployeesDirectoryMenu();
                                 		break;
                                 	case 5:
                                 		break;
                                 	default:
                                 		System.out.println();
                                 		System.out.println("Warning: Unrecognized choice...");
                                 		viewEmployeesDirectoryMenu();
                                 }
                                 
                             } else {
                            	 System.out.println();
//                            	 System.out.println("Warning: Employee not found");
//                            	 System.out.println(String.format("%-5s%-16s%18s%16s%5s", "","","Warning: Employee not found","",""));
                         		System.out.println(String.format("%-5s%-50s%5s", "","*** Warning: Employee not found ***",""));

                            	 System.out.println();
                            	 viewEmployeesDirectoryMenu();
                             }

                    	} else {
                    		 System.out.println();
                             System.out.println("Warning: Unrecognized choice...");
                             System.out.println();
                             viewEmployeesDirectoryMenu();
                    	}
                        break;
                    case 2:
                    	viewEmployeesDirectoryMenu();
                        break;
                    case 3:
                    	System.out.println("\n");
                    	mainMenuView();
                        break;
                    default:
                        System.out.println();
                        System.out.println("Warning: Unrecognized choice...");
                        viewEmployeesDirectoryMenu();
                }
        		break;
            case 5:
            	System.out.println("\n");
            	mainMenuView();
                break;
            default:
                System.out.println();
                System.out.println("Warning: Unrecognized choice...");
                viewEmployeesDirectoryMenu();
        }
	}
	
	
	/**
	 * Handles Retrieval of Borrowed Items
	 * @param selectedEmployee
	 */
	private void getBorrowedReferences(Employee selectedEmployee) {
		
		ArrayList<Reference>selectedEmpBorrowedRef = selectedEmployee.getBorrowedReferences();
		
		if(!selectedEmpBorrowedRef.isEmpty()) {
			
			OutputHelper.dispBorrowedReferences(selectedEmpBorrowedRef);
			
			
			System.out.println(String.format("%-5s%-28s%2s%-24s%5s", "","[1] Select Reference (by code)","","[2] Back (Employee Directory)",""));
			System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[3] Back (Main Menu)","","",""));
			System.out.print(String.format("%13s","Choice: "));
			
			
			
			
			
			
			
//			
//			
//			System.out.println("\n[1] Select Reference (by code)");
//			System.out.println("[2] Back (Employee Directory)");
//			System.out.println("[3] Back (Main Menu)");
//			System.out.print("\nChoice: ");
			int inputBorrowRef = InputHelper.scanInt(1,3);

			
			switch(inputBorrowRef) {
					case 1:
//						System.out.print("Select Reference (by code): ");
						System.out.println();
		        		System.out.print(String.format("%-5s%-28s", "","Select Reference (by code): "));

						
						int selectedRefCode = InputHelper.scanInt();
						Reference selectedReference = null;
						
						for(Reference refs: selectedEmpBorrowedRef) {
							if(refs.getCode() == selectedRefCode) {
								selectedReference = refs;
								break;
							}
						}
						
						if(selectedReference != null) {
							System.out.println();
							OutputHelper.displaySelectedReference(selectedReference, "Selected Reference");
							
				        	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Date Borrowed: ", selectedReference.getDateBorrowed(),""));
				        	System.out.println(String.format("%-5s%-23s%-30s%5s", "","Date to Return: ", selectedReference.getDateToReturn(),""));
				    		System.out.println(String.format("%-5s%-50s%5s", "","--------------------------------------------------",""));
				    		
				    		System.out.println(String.format("%-5s%-29s%2s%-24s%5s", "","[1] Return References","","[2] Report as Lost",""));
							System.out.println(String.format("%-5s%-24s%2s%-24s%5s", "","[3] Back (Employee Directory)","","[4] Back (Main Menu)",""));
							System.out.print(String.format("%13s","Choice: "));
							int inputBorrowedRefAction = InputHelper.scanInt(1,4);
			                 
							switch(inputBorrowedRefAction) {
								case 1:
									// Return reference
									returnReference(selectedEmployee, selectedReference);
									break;
								case 2:
			                 		// Report as lost
									reportLostReference(selectedEmployee, selectedReference);
									break;
								case 3:
			                 		System.out.println();
			                 		viewEmployeesDirectoryMenu();
			                 		break;
								case 4:
			                 		System.out.println("\n");
			                    	mainMenuView();
			                 		break;
			                 	default:
			                 		System.out.println();
			                 		System.out.println("Warning: Unrecognized choice...");
			                 		getBorrowedReferences(selectedEmployee);
			                 }
						} else {
							System.out.println("\nWarning: Borrowed Reference not Found");
							getBorrowedReferences(selectedEmployee);
						}
						break;
					case 2:
						System.out.println();
						viewEmployeesDirectoryMenu();
						break;
					case 3:
						System.out.println("\n");
                    	mainMenuView();
                 		break;
                 	default:
                 		System.out.println();
                 		System.out.println("Warning: Unrecognized choice...");
                 		getBorrowedReferences(selectedEmployee);
                 }
			
		} else {
     		System.out.println(String.format("%-5s%-50s%5s", "","*** No Borrowed Reference Found ***",""));
			System.out.println();
			viewEmployeesDirectoryMenu();
		}
	}
	
	
	/**
	 * Manage Lost References
	 * @param selectedEmployee
	 */
	private void getLostReferences(Employee selectedEmployee) {
		
		ArrayList<Reference>selectedEmpLostRef = selectedEmployee.getLostReferences();

		System.out.println("\nLost Reference(s)");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Code\t| Title		| Penalty Amount");
		System.out.println("-----------------------------------------------------------------");

		if(!selectedEmpLostRef.isEmpty()) {
			for (Reference reference : selectedEmpLostRef) {
				System.out.println(reference.getCode() + "\t " 
						+ reference.getTitle() + "\t " 
						+ reference.getReplacementAmount());
			}
			System.out.println("-----------------------------------------------------------------");
			System.out.print("Total Lost Penalty: " +  selectedEmployee.getTotalLostPenalty() + " \n");
			
			System.out.println("\n[1] Select Reference (by code)");
			System.out.println("[2] Back (Employee Directory)");
			System.out.println("[3] Back (Main Menu)");
			System.out.print("\nChoice: ");
			int inputBorrowRef = InputHelper.scanInt(1,3);
             
			switch(inputBorrowRef) {
					case 1:
						System.out.print("Select Reference (by code): ");
						int selectedRefCode = InputHelper.scanInt();
						Reference selectedReference = null;
						
						for(Reference refs: selectedEmpLostRef) {
							if(refs.getCode() == selectedRefCode) {
								selectedReference = refs;
								break;
							}
						}
						
						if(selectedReference != null) {
							System.out.println();
							displaySelectedReference(selectedReference);
							System.out.println("Date Borrowed: " + selectedReference.getDateBorrowed());
							System.out.println("Date to Return: " + selectedReference.getDateToReturn());
							System.out.println();
							
							System.out.println("[1] Pay/Replace Lost References");
							System.out.println("[2] Back (Employee Directory)");
							System.out.println("[3] Back (Main Menu)");
							System.out.print("\nChoice: ");
							int inputBorrowedRefAction = InputHelper.scanInt(1,3);
			                 
							switch(inputBorrowedRefAction) {
								case 1:
									// Payment/Replacement of Item
									selectedEmployee.getLostReferencesByCode(selectedReference.getCode());
									System.out.println("Reference Successfully Paid/Replaced");
									System.out.println();
									viewEmployeesDirectoryMenu();
									break;
								case 2:
									System.out.println();
									viewEmployeesDirectoryMenu();
									break;
								case 3:
			                 		System.out.println("\n");
			                    	mainMenuView();
			                 		break;
			                 	default:
			                 		System.out.println();
			                 		System.out.println("Warning: Unrecognized choice...");
			                 		getBorrowedReferences(selectedEmployee);
			                 }

						} else {
							System.out.println("\nWarning: Borrowed Reference not Found");
							getBorrowedReferences(selectedEmployee);
						}
						
						break;
					case 2:
						System.out.println();
						viewEmployeesDirectoryMenu();
						break;
					case 3:
						System.out.println("\n");
                    	mainMenuView();
                 		break;
                 	default:
                 		System.out.println();
                 		System.out.println("Warning: Unrecognized choice...");
                 		getBorrowedReferences(selectedEmployee);
                 }
		} else {
			System.out.println("No Lost Reference Found.");
			System.out.println("-----------------------------------------------------------------");
			System.out.println();
			viewEmployeesDirectoryMenu();
		}
	}
	
	
	private void getLateReturnedReferences(Employee selectedEmployee){
		
		ArrayList<Reference>selectedEmpLateRetRef = ((Trainee)selectedEmployee).getLateReturnedReferences();
		
		System.out.println("\nLate Returned Reference(s)");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Code\t| Title		| Fines	| # of Days	| Total");
		System.out.println("-----------------------------------------------------------------");

		
		int dueDays = 0;
		if(!selectedEmpLateRetRef.isEmpty()) {
			for (Reference reference : selectedEmpLateRetRef) {
				
				dueDays = InputHelper.getOverDueDays(reference.getDateToReturn(), reference.getDateReturned());
				System.out.println(reference.getCode() + "\t " 
						+ reference.getTitle() + "\t " 
						+ reference.getFines() + "\t "
						+ dueDays + "\t" 
						+ reference.getFines() * dueDays);
			}
			System.out.println("-----------------------------------------------------------------");
			System.out.print("Overall Total Fine: " +  ((Trainee)selectedEmployee).getTotalLateFines() + " \n");
			
			System.out.println("\n[1] Select Reference (by code)");
			System.out.println("[2] Back (Employee Directory)");
			System.out.println("[3] Back (Main Menu)");
			System.out.print("\nChoice: ");
			int inputLateBorrowRef = InputHelper.scanInt(1,3);
             
			switch(inputLateBorrowRef) {
					case 1:
						System.out.print("Select Reference (by code): ");
						int selectedRefCode = InputHelper.scanInt();
						Reference selectedReference = null;
						
						for(Reference refs: selectedEmpLateRetRef) {
							if(refs.getCode() == selectedRefCode) {
								selectedReference = refs;
								break;
							}
						}
						
						if(selectedReference != null) {
							System.out.println();
							displaySelectedReference(selectedReference);
							System.out.println("Date Borrowed: " + selectedReference.getDateBorrowed());
							System.out.println("Date to Return: " + selectedReference.getDateToReturn());
							System.out.println("Date Returned: " + selectedReference.getDateToReturn());
							System.out.println("Fines: " + dueDays);
							
							System.out.println("[1] Pay Reference Fines");
							System.out.println("[2] Back (Employee Directory)");
							System.out.println("[3] Back (Main Menu)");
							System.out.print("\nChoice: ");
							int inputBorrowedRefAction = InputHelper.scanInt(1,3);
			                 
							switch(inputBorrowedRefAction) {
								case 1:
									// Payment/Replacement of Item
									selectedEmployee.getLostReferencesByCode(selectedReference.getCode());
									System.out.println("Reference Successfully Paid");
									System.out.println();
									viewEmployeesDirectoryMenu();
									break;
								case 2:
									System.out.println();
									viewEmployeesDirectoryMenu();
									break;
								case 3:
			                 		System.out.println("\n");
			                    	mainMenuView();
			                 		break;
			                 	default:
			                 		System.out.println();
			                 		System.out.println("Warning: Unrecognized choice...");
			                 		getBorrowedReferences(selectedEmployee);
			                 }

						} else {
							System.out.println("\nWarning: Borrowed Reference not Found");
							getBorrowedReferences(selectedEmployee);
						}
						
						break;
					case 2:
						System.out.println();
						viewEmployeesDirectoryMenu();
						break;
					case 3:
						System.out.println("\n");
                    	mainMenuView();
                 		break;
                 	default:
                 		System.out.println();
                 		System.out.println("Warning: Unrecognized choice...");
                 		getBorrowedReferences(selectedEmployee);
                 }
		} else {
			System.out.println("No Late Returned Reference Found.");
			System.out.println("-----------------------------------------------------------------");
			System.out.println();
			viewEmployeesDirectoryMenu();
		}
	}

	
	/**
	 * Handles returning of reference
	 * @param employee
	 * @param reference
	 */
	private void returnReference(Employee employee, Reference reference) {
		
		System.out.println();
		System.out.print(String.format("%31s","Return Date (mm/dd/yyyy): "));
		String returnDate = InputHelper.scanDate();
		
 		System.out.print(String.format("%-5s%-41s", "","Return Reference? [1] Confirm [2] Cancel: "));
		int confirmation = InputHelper.scanInt(1,2);
		
		switch(confirmation) {
			case 1: 
				reference.setDateReturned(returnDate);
				int dueDayCount = InputHelper.getOverDueDays(reference.getDateToReturn(), reference.getDateReturned());
				
				// Add reference into late records
				if( (dueDayCount > 0) && (employee.getType().equals(EmployeeType.TRAINEE))) {
					System.out.println();
		     		System.out.println(String.format("%-5s%-50s%5s", "","** Item is already due for " + dueDayCount + (dueDayCount>1?" days **":" day **"),""));
		     		System.out.println(String.format("%-5s%-50s%5s", "","** Employee will be fined " + dueDayCount * reference.getFines() + " **",""));

					// Records late returned item
					((Trainee)this.database.getEmployeeByID(employee.getID())).addLateReturnedReferences(reference);
				}
				
				this.database.getReferenceByCode(reference.getCode()).returnReference();
				this.database.getEmployeeByID(employee.getID()).returnReference(reference.getCode());

	     		System.out.println(String.format("%-5s%-50s%5s", "","** Item succesfully returned. **",""));

				System.out.println();
				viewEmployeesDirectoryMenu();
				break;
			case 2:
				System.out.println();
	     		System.out.println(String.format("%-5s%-50s%5s", "","** Return Process Cancelled. **",""));

				System.out.println();
         		getBorrowedReferences(employee);
				break;
		}
		
	}
	
	
	/**
	 * Handles recording of lost reference
	 * @param employee
	 * @param reference
	 */
	private void reportLostReference(Employee employee, Reference reference) {
		
 		System.out.print(String.format("%-5s%-50s", "","Report Reference as Lost? [1] Confirm [2] Cancel: "));
		int confirmation = InputHelper.scanInt(1,2);
		
		switch(confirmation) {
			case 1: 

				this.database.getEmployeeByID(employee.getID()).reportLostReference(reference.getCode());;
				
				System.out.println();
	     		System.out.println(String.format("%-5s%-50s%5s", "","** Lost reference successfully recorded. **",""));
	     		System.out.println(String.format("%-5s%-50s%5s", "","** Employee will be fined " + reference.getReplacementAmount() + " **",""));
				System.out.println();
				viewEmployeesDirectoryMenu();
				break;
			case 2:
				System.out.println();
	     		System.out.println(String.format("%-5s%-50s%5s", "","** Report Process Cancelled. **",""));
				System.out.println();
	     		getBorrowedReferences(employee);
				break;
		}
	}
	
	
	/**
	 * Library Manager View
	 */
	private void manageLibraryMenu(boolean override) {
		
		String inputPIN;
		
		/* Override menu if already used from its child view
		 */
		if(!override) {
			System.out.print(">> Enter PIN: ");
	        inputPIN = InputHelper.scanString();
		} else {
			inputPIN = Setting.PIN;
		}
        
        if(inputPIN.equals(Setting.PIN)) {
        	if(!override) { System.out.println("*** Access Granted!"); }
        	System.out.println();
        	System.out.println("Library Manager");
    		System.out.println("----------------------------------------------------------");
            System.out.println("[1] References");
            System.out.println("[2] Employees");
            System.out.println("[3] Back (Main Menu)");
            System.out.print("\nChoice: ");
            int inputManageLibraryMenu = InputHelper.scanInt();
            
             switch(inputManageLibraryMenu) {
                case 1:
                	// Redirects to reference manager menu
                	System.out.println("\n");
                	referenceManagerMenu();
                    break;
                case 2:
                	// Redirects to employee manager menu
                	System.out.println("\n");
                	employeeManagerMenu();
                    break;
                case 3:
                	System.out.println("\n");
                	mainMenuView();
                    break;
                default:
                    System.out.println("\n");
                    System.out.println("Warning: Unrecognized choice...");
                    manageLibraryMenu(true);
            }
        } else {
            System.out.println("*** Warning: Invalid PIN, Access Denied. ");
            System.out.println();
            mainMenuView();
        }
        return;
	}
	
	
	
	/**
	 * Display Reference Details
	 * Returns true if result is not empty, otherwise false
	 * @param type
	 * @return
	 */
	private ArrayList <Reference> getReferences(int type) {
		
		// Temporary List for Query Result
		ArrayList <Reference> tempList = new ArrayList<Reference>();
		
		switch(type) {
			case 1:
				tempList = this.database.getReferences();
				break;
			case 2:
				tempList = this.database.getReferencesByType(ReferenceType.ACTION_REFERENCE);
				break;
			case 3: 
				tempList = this.database.getReferencesByType(ReferenceType.VIDEO_CD);
				break;
			case 4:
				tempList = this.database.getReferencesByType(ReferenceType.FLASH_CARD);
				break;
			case 5:
				tempList = this.database.getReferencesByType(ReferenceType.MAGAZINE);
				break;
			case 6:
				tempList = this.database.getReferencesByType(ReferenceType.OTHER_MATERIAL);
				break;
			case 7:
				System.out.print("Enter Title: ");
		        String inputSearchTitle = InputHelper.scanString();
				System.out.println();
		        tempList = this.database.getReferencesByTitle(inputSearchTitle);
				break;
			default: 
				System.out.println("Warning: Unrecognized choice...");
		}

		return tempList;
	}
	
	
	
	/**
	 * Display Selected Reference
	 * @param reference
	 */
	private void displaySelectedReference(Reference reference) {
		
		System.out.println("Code: " + reference.getCode());	
		System.out.println("Title: " + reference.getTitle());	

		switch(reference.getType()) 
        { 
            case ReferenceType.ACTION_REFERENCE: 
            	System.out.println("Author: " + ((ActionReference)reference).getAuthor());	
                break; 
            case ReferenceType.VIDEO_CD: 
            	System.out.println("Director: " + ((VideoCD)reference).getDirector());	
            	System.out.println("Duration: " + ((VideoCD)reference).getDuration());	
                break; 
            case ReferenceType.FLASH_CARD: 
            	System.out.println("Creator: " + ((FlashCard)reference).getCreator());	
            	System.out.println("Number of Card: " + ((FlashCard)reference).getNumberOfCard());
                break;
            case ReferenceType.MAGAZINE: 
            	System.out.println("Editor: " + ((Magazine)reference).getEditor());	
            	System.out.println("Volume: " + ((Magazine)reference).getVolume());
                break;
            case ReferenceType.OTHER_MATERIAL: 
            	System.out.println("Creator: " + ((OtherMaterial)reference).getCreator());	
            	System.out.println("Description: " + ((OtherMaterial)reference).getDescription()); 
                break;
            default: 
                System.out.println("Reference Type not Valid..."); 
        } 
		
		System.out.println("Maximum Borrow Duration: " + reference.getBorrowDurationLimit() + 
				(reference.getBorrowDurationLimit() > 1? " Days" : " Day")); 
		System.out.println("Stock: " + reference.getStock());
		System.out.println("Type: " + reference.getType());
		
	}
	
	
	
	/**
	 * Display Employee Details
	 * @param type
	 * @return
	 */
	private ArrayList <Employee> getEmployees(int type) {
		
		// Temporary List for Query Result
		ArrayList <Employee> tempList = new ArrayList<Employee>();
		
		switch(type) {
			case 1:
				tempList = this.database.getEmployees();
				break;
			case 2:
				tempList = this.database.getEmployeesByType(EmployeeType.TRAINEE);
				break;
			case 3: 
				tempList = this.database.getEmployeesByType(EmployeeType.TRAINER);
				break;
			case 4:
				System.out.println();
				System.out.print(String.format("%17s","Enter Name: "));
		        String inputSearchName = InputHelper.scanString();
				tempList = this.database.getEmployeesByName(inputSearchName);
				break;
			default: 
				System.out.println("Warning: Unrecognized choice...");
		}
		
		return tempList;
	}
	
	
	/**
	 * Display Selected Employee
	 * @param employee
	 */
	private void displaySelectedEmployee(Employee employee) {
		
		System.out.println("ID: " + employee.getID());	
		System.out.println("Name: " + employee.getName());	
		System.out.println("Gender: " + employee.getGender());	
		System.out.println("Address: " + employee.getAddress());	
		System.out.println("Contact Number: " + employee.getContactNum());	
		System.out.println("Position: " + employee.getPosition());	
		System.out.println("Type: " + employee.getType());
		
	}
	 
	
	
	/**
	 * Display Reference manager menu
	 */
	private void referenceManagerMenu() {

		System.out.println("Reference Manager");
		System.out.println("----------------------------------------------------------");
        System.out.println("[1] Add Reference");
        System.out.println("[2] Edit/Delete Reference");
        System.out.println("[3] Back (Library Manage Menu)");
        System.out.println("[4] Back (Main Menu)");
        System.out.print("\nChoice: ");
        int inputReferenceManagerMenu = InputHelper.scanInt();

        switch(inputReferenceManagerMenu) {
            case 1:
            	// Add Reference
            	System.out.println("\n");
            	addReferenceHandler();
                break;
            case 2:
                // Delete/Edit Reference
            	System.out.println("\n");
            	referenceRecordHandler();
                break;
            case 3:
            	System.out.println("\n");
            	manageLibraryMenu(true);
                break;
            case 4:
            	System.out.println("\n");
            	mainMenuView();
                break;
            default:
                System.out.println();
                System.out.println("Warning: Unrecognized choice...");
                referenceManagerMenu();
        }
        
	}
	
	
	/**
	 * Handles adding of reference
	 */
	private void addReferenceHandler() {
		
		Reference newReference = null;
		
		System.out.println("Add New Reference");
		System.out.println("----------------------------------------------------------");
		System.out.println("Select Type   [1]Action Reference [2]Video CD  ");
		System.out.println("	      [3]Flash Card [4]Magazine ");
		System.out.println("	      [5]Other Material [0] Back(Reference Manager Menu)");
		System.out.print("Type: ");
		int referenceType = InputHelper.scanInt(0,5);
		
		String title;
		int stock;
		
		switch(referenceType) 
        { 
			case 0:
				System.out.println();
				referenceManagerMenu();
				break;
            case 1: 
            	System.out.println();
            	System.out.println("New Action Reference");
            	System.out.println("--------------------");
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Author: ");
            	String author = InputHelper.scanString();
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	newReference = new ActionReference(title, author, stock,
        											LostPenalty.ACTION_REFERENCES, 
        											FinesAmount.ACTION_REFERENCES, 
        											BorrowDurationLimit.ACTION_REFERENCES);
            	break; 
            case 2: 
            	System.out.println();
            	System.out.println("New Video CD");
            	System.out.println("--------------------");
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Director: ");
            	String director = InputHelper.scanString();
            	System.out.print("Duration: ");
            	double duration = InputHelper.scanDouble();
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	newReference = new VideoCD(title, director, duration, stock, 
        									LostPenalty.VIDEO_CDs, 
        									FinesAmount.VIDEO_CDs, 
        									BorrowDurationLimit.VIDEO_CDs);
                break; 
            case 3: 
            	System.out.println();
            	System.out.println("New Flash Card");
            	System.out.println("--------------------");
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Creator: ");
            	String creatorFC = InputHelper.scanString();
            	System.out.print("Number of Card: ");
            	int numberOfCard = InputHelper.scanInt(1);
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	newReference = new FlashCard(title, creatorFC, numberOfCard, stock,
        									LostPenalty.FLASH_CARDS, 
        									FinesAmount.FLASH_CARDS, 100);
                break;
            case 4: 
            	System.out.println();
            	System.out.println("New Magazine");
            	System.out.println("--------------------");
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Editor: ");
            	String editor = InputHelper.scanString();
            	System.out.print("Volume: ");
            	int volume = InputHelper.scanInt(1);
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	newReference = new Magazine(title, editor, volume, stock,
        									LostPenalty.MAGAZINES, 
        									FinesAmount.FLASH_CARDS, 
        									BorrowDurationLimit.MAGAZINES);
                break;
            case 5: 
            	System.out.println();
            	System.out.println("New Material");
            	System.out.println("--------------------");
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Creator: ");
            	String creatorOM = InputHelper.scanString();
            	System.out.print("Description: ");
            	String description = InputHelper.scanString();
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	newReference = new OtherMaterial(title, creatorOM, description, stock, 
    								LostPenalty.OTHER_MATERIALS, 
    								FinesAmount.OTHER_MATERIALS, 
    								BorrowDurationLimit.OTHER_MATERIALS);
            	break;
            default: 
                System.out.println("Reference Type not Valid. Please try again."); 
        } 
		
		System.out.println("--------------------");
		System.out.print("Add Reference? [1] Confirm [2] Cancel: ");
		int confirmation = InputHelper.scanInt(1,2);
		
		switch(confirmation) {
			case 1: 
				database.addReference(newReference);
				System.out.println("**New Item Added Succesfully..");
				break;
			case 2:
				System.out.println("Adding Process Cancelled.");
				break;
		}
		System.out.println("\n");
    	referenceManagerMenu();
		
	}
	
	
	/**
	 * Handles References
	 */
	private void referenceRecordHandler() {
		System.out.println("Update/Delete Reference");
		System.out.println("----------------------------------------------------------");
		System.out.println("[1] View All References");
	    System.out.println("[2] Search Reference (by title)");
	    System.out.println("[3] Back (Reference Manager Menu)");
        System.out.println("[4] Back (Main Menu)");
        System.out.print("\nChoice: ");
        int inputReferenceRecordHandler = InputHelper.scanInt();

        boolean resultNotEmpty = false;
        
        switch(inputReferenceRecordHandler) {
            case 1: case 2:
            	/* View references according to choice,
        		 */
        		ArrayList<Reference> queryResult = getReferences((inputReferenceRecordHandler==2? 7: inputReferenceRecordHandler));
        		System.out.println("Reference(s)");
        		System.out.println("----------------------------------------------------------");
        		System.out.println("Code\t| Title			| Stock		| Type");
        		System.out.println("----------------------------------------------------------");

        		if(!queryResult.isEmpty()) {
        			resultNotEmpty = true;
        			for (Reference reference : queryResult) {
        				System.out.println(reference.getCode() + "\t " 
        						+ reference.getTitle() + "\t " 
        						+ reference.getStock() + "\t "
        						+ reference.getType());
        			}
        		} else {
        			System.out.println("No Reference Found. ");
        		}
        		System.out.println("----------------------------------------------------------");

        		break;
            case 3:
            	System.out.println("\n");
            	referenceManagerMenu();
                break;
            case 4:
            	System.out.println("\n");
            	mainMenuView();
                break;
            default:
                System.out.println();
                System.out.println("Warning: Unrecognized choice...");
                referenceManagerMenu();
        }
        
        if(resultNotEmpty) {
        	System.out.print("Select Reference (by code): ");
        	int selectedCode = InputHelper.scanInt();
        	Reference selectedReference = this.database.getReferenceByCode(selectedCode);
     		System.out.println();
     		if(selectedReference != null) {
     			System.out.println("Selected Reference");
     			System.out.println("-------------------");
     			displaySelectedReference(selectedReference);
     		
     			// prompt user whether to delete or update selected reference
     			System.out.println();
     			System.out.println("[1] Update [2] Delete [3] Back (Reference Manager Menu)");
     	        System.out.print("Choice: ");
     	        int selectedAction = InputHelper.scanInt(1,3);
     	       
     			switch(selectedAction) {
     				case 1:
     					updateReferenceHandler(selectedCode);
     					break;
     				case 2:
     					System.out.print("\nDelete Record? [1] Confirm [2] Cancel: ");
     					int inputConfirmation = InputHelper.scanInt(1,2);
     					switch(inputConfirmation) {
     						case 1:
     							// Delete selected reference
     							this.database.deleteReference(selectedCode);
     							System.out.println("\n**Reference deleted succesfully..");
     							System.out.println();
     	     	                referenceManagerMenu();
     							break;
     						case 2:
     							// Cancel
     							System.out.println();
     	     	                referenceManagerMenu();
     							break;
     					}
     					break;
     				case 3:
     					System.out.println();
     	                referenceManagerMenu();
     					break;
     			}
     			
     		} else {
     			System.out.println("Warning: Reference not found");
     		}
        } else {
        	System.out.println("\n");
        	referenceRecordHandler();
        }

	}
	
	

	/**
	 * Handles update process of references
	 * @param code
	 */
	private void updateReferenceHandler(int code) {
		
		Reference selectedReference = this.database.getReferenceByCode(code);
		
		String title;
		int stock;
		
		System.out.println("\nNew Reference Details");
		System.out.println("-------------------");
		
		switch(selectedReference.getType()) 
        { 
            case ReferenceType.ACTION_REFERENCE: 
            	
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Author: ");
            	String author = InputHelper.scanString();
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	((ActionReference)selectedReference).setTitle(title);
            	((ActionReference)selectedReference).setAuthor(author);
            	((ActionReference)selectedReference).setStock(stock);
            	
                break; 
            case ReferenceType.VIDEO_CD: 
            	
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Director: ");
            	String director = InputHelper.scanString();
            	System.out.print("Duration: ");
            	double duration = InputHelper.scanDouble();
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	((VideoCD)selectedReference).setTitle(title);
            	((VideoCD)selectedReference).setDirector(director);
            	((VideoCD)selectedReference).setDuration(duration);
            	((VideoCD)selectedReference).setStock(stock);
            	
                break; 
            case ReferenceType.FLASH_CARD: 
            	
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Creator: ");
            	String creatorFC = InputHelper.scanString();
            	System.out.print("Number of Card: ");
            	int numberOfCard = InputHelper.scanInt(1);
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	((FlashCard)selectedReference).setTitle(title);
            	((FlashCard)selectedReference).setCreator(creatorFC);
            	((FlashCard)selectedReference).setNumberOfCard(numberOfCard);
            	((FlashCard)selectedReference).setStock(stock);
            	
                break;
            case ReferenceType.MAGAZINE: 
            	
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Editor: ");
            	String editor = InputHelper.scanString();
            	System.out.print("Volume: ");
            	int volume = InputHelper.scanInt(1);
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	((Magazine)selectedReference).setTitle(title);
            	((Magazine)selectedReference).setEditor(editor);
            	((Magazine)selectedReference).setVolume(volume);
            	((Magazine)selectedReference).setStock(stock);
            	
                break;
            case ReferenceType.OTHER_MATERIAL: 
            	
            	System.out.print("Title: ");
            	title = InputHelper.scanString();
            	System.out.print("Creator: ");
            	String creatorOM = InputHelper.scanString();
            	System.out.print("Description: ");
            	String description = InputHelper.scanString();
            	System.out.print("Stock: ");
            	stock = InputHelper.scanInt(0);
            	
            	((OtherMaterial)selectedReference).setTitle(title);
            	((OtherMaterial)selectedReference).setCreator(creatorOM);
            	((OtherMaterial)selectedReference).setDescription(description);
            	((OtherMaterial)selectedReference).setStock(stock);
            	
                break;
            default: 
                System.out.println("Reference Type not Valid..."); 
        } 
		
		System.out.println("--------------------");
		System.out.print("Update Reference? [1] Confirm [2] Cancel: ");
		int confirmation = InputHelper.scanInt(1,2);
		
		switch(confirmation) {
			case 1: 
				database.updateReference(selectedReference);
				System.out.println("**Reference Update Succesfully..");
				System.out.println("\n");
				referenceRecordHandler();
				break;
			case 2:
				System.out.println("**Adding Process Cancelled.");
				System.out.println("\n");
				referenceRecordHandler();
				break;
		}

	}
	
	

	/**
	 * Display Employee Manager Menu
	 */
	private void employeeManagerMenu() {
		System.out.println("Employee Manager");
		System.out.println("----------------------------------------------------------");
		System.out.println("[1] Add Employee");
	    System.out.println("[2] Edit/Delete Employee");
        System.out.println("[3] Back (Library Manager Menu)");
        System.out.println("[4] Back (Main Menu)");
        System.out.print("\nChoice: ");
        int inputEmployeeManagerMenu = InputHelper.scanInt();
        
        switch(inputEmployeeManagerMenu) {
	        case 1:
	        	// Add Employee
	        	System.out.println("\n");
	        	addEmployeeHandler();
	            break;
	        case 2:
	             // Delete/Edit Employee
	        	employeeRecordHandler();
	            break;
	        case 3:
	        	System.out.println("\n");
	        	manageLibraryMenu(true);
	            break;
	        case 4:
	        	System.out.println("\n");
	        	mainMenuView();
	            break;
	        default:
	            System.out.println();
	            System.out.println("Warning: Unrecognized choice...");
	            employeeManagerMenu();
	    }

	}
	
	
	/**
	 * Handles adding of employee
	 */
	private void addEmployeeHandler() {
		
		Employee newEmployee = null;
		
		System.out.println("Add New Employee");
		System.out.println("----------------------------------------------------------");
		System.out.println("Select Type   [1] Employee [2] Trainee ");
		System.out.println("Select Type   [3] Trainer  [0] Back (Employee Manager Menu)");
		System.out.print("Type: ");
		int employeeType = InputHelper.scanInt(0,3);
		
		String name;
		String gender;
		String address;
		String contactNum;
		String position;
		
		switch(employeeType) 
        { 
			case 0:
				System.out.println();
				employeeManagerMenu();
				break;
            case 1: 
            	System.out.println();
            	System.out.println("New Employee");
            	System.out.println("--------------------");
            	System.out.print("Name: ");
            	name = InputHelper.scanString();
            	System.out.print("Gender: ");
            	gender = InputHelper.scanString();
            	System.out.print("Address: ");
            	address = InputHelper.scanString();
            	System.out.print("Contact Number: ");
            	contactNum = InputHelper.scanString();
            	System.out.print("Position: ");
            	position = InputHelper.scanString();
            	
            	newEmployee = new Employee(name, gender, address, contactNum, position);
            	
            	break; 
            case 2: 
            	System.out.println();
            	System.out.println("New Trainee");
            	System.out.println("--------------------");
            	System.out.print("Name: ");
            	name = InputHelper.scanString();
            	System.out.print("Gender: ");
            	gender = InputHelper.scanString();
            	System.out.print("Address: ");
            	address = InputHelper.scanString();
            	System.out.print("Contact Number: ");
            	contactNum = InputHelper.scanString();
            	System.out.print("Position: ");
            	position = InputHelper.scanString();
            	
            	newEmployee = new Trainee(name, gender, address, contactNum, position);
            	
                break; 
            case 3: 
            	System.out.println();
            	System.out.println("New Trainer");
            	System.out.println("--------------------");
            	System.out.print("Name: ");
            	name = InputHelper.scanString();
            	System.out.print("Gender: ");
            	gender = InputHelper.scanString();
            	System.out.print("Address: ");
            	address = InputHelper.scanString();
            	System.out.print("Contact Number: ");
            	contactNum = InputHelper.scanString();
            	System.out.print("Position: ");
            	position = InputHelper.scanString();
            	
            	newEmployee = new Trainer(name, gender, address, contactNum, position);
            	
                break;
            default: 
                System.out.println("Employee Type not Valid. Please try again."); 
        } 
		
		System.out.println("--------------------");
		System.out.print("Add Employee? [1] Confirm [2] Cancel: ");
		int confirmation = InputHelper.scanInt(1,2);
		
		switch(confirmation) {
			case 1: 
				database.addEmployee(newEmployee);
				System.out.println("**New Employee Added Succesfully..");
				break;
			case 2:
				System.out.println("Adding Process Cancelled.");
				break;
		}
		
		System.out.println("\n");
    	employeeManagerMenu();
		
	}
	
	
	/**
	 * Handles Employees
	 */
	private void employeeRecordHandler() {
		System.out.println("Update/Delete Employees");
		System.out.println("----------------------------------------------------------");
		System.out.println("[1] View All Employees");
	    System.out.println("[2] Search Employee (by name)");
	    System.out.println("[3] Back (Employee Manager Menu)");
        System.out.println("[4] Back (Main Menu)");
        System.out.print("\nChoice: ");
        int inputEmployeeRecordHandler = InputHelper.scanInt();

        boolean resultNotEmpty = false;
        
        switch(inputEmployeeRecordHandler) {
            case 1: case 2:
            	/* View Employees according to choice,
        		 */
        		ArrayList<Employee> queryResult = getEmployees((inputEmployeeRecordHandler==2? 7: inputEmployeeRecordHandler));
        		System.out.println("Employee(s)");
        		System.out.println("----------------------------------------------------------");
        		System.out.println("ID\t| Name		| Gender	| Position   |Type");
				System.out.println("----------------------------------------------------------");

        		if(!queryResult.isEmpty()) {
        			resultNotEmpty = true;
        			for (Employee employee : queryResult) {
        				System.out.println(employee.getID() + "\t " 
        						+ employee.getName() + "\t " 
        						+ employee.getGender() + "\t "
        						+employee.getPosition() + "\t"
        						+ employee.getType());
        			}
        		} else {
        			System.out.println("No Employee Found. ");
        		}
        		System.out.println("----------------------------------------------------------");

        		break;
            case 3:
            	System.out.println("\n");
            	employeeManagerMenu();
                break;
            case 4:
            	System.out.println("\n");
            	mainMenuView();
                break;
            default:
                System.out.println();
                System.out.println("Warning: Unrecognized choice...");
                employeeManagerMenu();
        }
        
        if(resultNotEmpty) {
        	System.out.print("Select Employee (by ID): ");
        	int selectedID = InputHelper.scanInt();
        	Employee selectedEmployee = this.database.getEmployeeByID(selectedID);
     		System.out.println();
     		if(selectedEmployee != null) {
     			System.out.println("Selected Employee");
     			System.out.println("-------------------");
     			displaySelectedEmployee(selectedEmployee);
     		
     			// prompt user whether to delete or update selected Employee
     			System.out.println();
     			System.out.println("[1] Update [2] Delete [3] Back (Employee Manager Menu)");
     	        System.out.print("Choice: ");
     	        int selectedAction = InputHelper.scanInt(1,3);
     	       
     			switch(selectedAction) {
     				case 1:
     					updateEmployeeHandler(selectedID);
     					break;
     				case 2:
     					System.out.print("\nDelete Record? [1] Confirm [2] Cancel: ");
     					int inputConfirmation = InputHelper.scanInt(1,2);
     					switch(inputConfirmation) {
     						case 1:
     							// Delete selected reference
     							this.database.deleteEmployee(selectedID);
     							System.out.println("\n**Reference deleted succesfully..");
     							System.out.println();
     	     	                employeeManagerMenu();
     							break;
     						case 2:
     							// Cancel
     							System.out.println();
     	     	                employeeManagerMenu();
     							break;
     					}
     					break;
     				case 3:
     					System.out.println();
     	                employeeManagerMenu();
     					break;
     			}
     			
     		} else {
     			System.out.println("Warning: Reference not found");
     		}
        } else {
        	System.out.println("\n");
        	employeeRecordHandler();
        }

	}
	
	
	
	/**
	 * Handles update process of employees
	 * @param id
	 */
	private void updateEmployeeHandler(int ID) {
		
		Employee selectedEmployee = this.database.getEmployeeByID(ID);
		
		String name;
		String gender;
		String position;
		String contact ;
		String address ;
		String type;
		
		System.out.println("\nNew Employee Details");
		System.out.println("-------------------");
		
		switch(selectedEmployee.getType()) 
        { 
            case EmployeeType.EMPLOYEE: 
            	
            	System.out.print("Name: ");
            	name = InputHelper.scanString();
            	System.out.print("Gender: ");
            	gender = InputHelper.scanString();
            	System.out.print("Adress:");
            	address = InputHelper.scanString();
            	System.out.print("Contact Number:");
            	contact = InputHelper.scanString();
               	System.out.print("Position: ");
            	position = InputHelper.scanString();
//            	System.out.print("Type: ");
//            	type = InputHelper.scanString();
            	
            	((Employee)selectedEmployee).setName(name);
            	((Employee)selectedEmployee).setGender(gender);
            	((Employee)selectedEmployee).setAddress(address);
            	((Employee)selectedEmployee).setContactNum(contact);
              	((Employee)selectedEmployee).setPosition(position);
//              	((Employee)selectedEmployee).setType(type);
            	
                break; 
            case EmployeeType.TRAINEE:
            	
				System.out.print("Name: ");
				name = InputHelper.scanString();
				System.out.print("Gender: ");
				gender = InputHelper.scanString();
	           	System.out.print("Adress:");
            	address = InputHelper.scanString();
            	System.out.print("Contact Number:");
            	contact = InputHelper.scanString();
               	System.out.print("Position: ");
            	position = InputHelper.scanString();
//               	System.out.print("Type: ");
//            	type = InputHelper.scanString();
	
				((Trainee)selectedEmployee).setName(name);
				((Trainee)selectedEmployee).setGender(gender);
	          	((Trainee)selectedEmployee).setAddress(address);
            	((Trainee)selectedEmployee).setContactNum(contact);
	           	((Trainee)selectedEmployee).setPosition(position);
//              	((Trainee)selectedEmployee).setType(type);
	
                break; 
  			case EmployeeType.TRAINER:
	
				System.out.print("Name: ");
				name = InputHelper.scanString();
				System.out.print("Gender: ");
				gender = InputHelper.scanString();
	           	System.out.print("Adress:");
            	address = InputHelper.scanString();
            	System.out.print("Contact Number:");
            	contact = InputHelper.scanString();
               	System.out.print("Position: ");
            	position = InputHelper.scanString();
//               	System.out.print("Type: ");
//            	type = InputHelper.scanString();

				((Trainer)selectedEmployee).setName(name);
				((Trainer)selectedEmployee).setGender(gender); 	
	          	((Trainer)selectedEmployee).setAddress(address);
            	((Trainer)selectedEmployee).setContactNum(contact);
	           	((Trainer)selectedEmployee).setPosition(position);
//              	((Trainer)selectedEmployee).setType(type);
                break;
            default: 
                System.out.println("Employee Type not Valid..."); 
        } 
		
		System.out.println("--------------------");
		System.out.print("Update Reference? [1] Confirm [2] Cancel: ");
		int confirmation = InputHelper.scanInt(1,2);
		
		switch(confirmation) {
			case 1: 
				database.updateEmployee(selectedEmployee);
				System.out.println("**Employee Update Succesfully..");
				System.out.println("\n");
				employeeRecordHandler();
				break;
			case 2:
				System.out.println("**Updating Process Cancelled.");
				System.out.println("\n");
				employeeRecordHandler();
				break;
		}

	}
	
	
	
	
	
	
}
