/**
 * 
 */
package com.aws.core;

import java.util.ArrayList;

/**
 * @author jonel.tapang
 *
 */
public class Database {
		
	private ArrayList<Employee> employees;  
	private ArrayList<Reference>  references; 
	 
	
	/* Constructors(Start)
	 */

	public Database() {
		employees = new ArrayList<Employee>();
		references = new ArrayList<Reference>();
	}
	
	public Database(ArrayList<Employee>  awsEmployees, ArrayList<Reference>  awsReferences) {
		this.employees = awsEmployees;
	    this.references = awsReferences;
	}
	
	/* Constructors(End)
	 */

	
	/* Employee Controller(Start)
     */
	
	/**
	 *  Add Employee to the list
	 * @param employee
	 */
	 public void addEmployee(Employee employee) {
		 this.employees.add(employee);
	 }
	 
	 
	 /**
	  * Get all employees
	  * @return
	  */
	 public ArrayList<Employee> getEmployees() {
	     return this.employees;
	 }
	 
	 
	 /**
	  * Returns ArrayList of employee based on the given type
	  * @param employeeType
	  * @return
	  */
	 public ArrayList<Employee> getEmployeesByType(String employeeType) {
		 ArrayList<Employee> filteredEmployee = new ArrayList<Employee>();
        
		 for (Employee employee : this.employees) {
			 if(employee.getClass().getSimpleName().equals(employeeType)) {
				 filteredEmployee.add(employee);
			 }
		 }
		 return filteredEmployee;
	 }
	 
	 
	 /**
	  * Returns ArrayList of employee based on the searched name
	  * @param name
	  * @return
	  */
	 public ArrayList<Employee> getEmployeesByName(String name) {
		 ArrayList<Employee> filteredEmployee = new ArrayList<Employee>();
        
		 for (Employee employee : this.employees) {
			 if(employee.getName().toLowerCase().contains(name.toLowerCase())) {
				 filteredEmployee.add(employee);
			 }
		 }
		 return filteredEmployee;
	 }
	 
	 
	 /**
	  * Returns an Employee object that matched to a given ID
	  * @param id
	  * @return
	  */
	 public Employee getEmployeeByID(int id) {
	        for (Employee employee : this.employees) {
	            if(employee.getID() == id) {
	                return employee;
	            }
	        }
	        return null;
	    }
	
	 
	 
	/* Employee Controller(End)
     */
     
     

	/* Reference Controller(Start)
     */
	 
	 /**
	  * Add Reference to the List
	  * @param reference
	  */
	 public void addReference(Reference reference) {
		 this.references.add(reference);
	 }
	 
	 
	 /**
	  * Get all references
	  * @return
	  */
	 public ArrayList<Reference> getReferences() {
	     return this.references;
	 }
	 
	 
	 /**
	  * Returns ArrayList of reference materials based on the given type
	  * @param referenceType
	  * @return
	  */
	 public ArrayList<Reference> getReferencesByType(String referenceType) {
		 ArrayList<Reference> filteredReference = new ArrayList<Reference>();
        
		 for (Reference reference : this.references) {
			 if(reference.getType().equals(referenceType)) {
				 filteredReference.add(reference);
			 }
		 }
		 return filteredReference;
	 }	 
	
	 
	 /**
	  * Returns ArrayList of reference materials based on the searched title
	  * @param title
	  * @return
	  */
	 public ArrayList<Reference> getReferencesByTitle(String title) {
		 ArrayList<Reference> filteredReference = new ArrayList<Reference>();
        
		 for (Reference reference : this.references) {
			 if(reference.getTitle().toLowerCase().contains(title.toLowerCase())) {
				 filteredReference.add(reference);
			 }
		 }
		 return filteredReference;
	 }
	 
	 
	 /**
	  * Returns a Reference intance that matched to a given code
	  * @param code
	  * @return
	  */
	 public Reference getReferenceByCode(int code) {
		 for (Reference reference : this.references) {
			 if(reference.getCode() == code) {
				 return reference;
			 }	
		 }
		 return null;
	 }
	 
	 
	 /**
	  * Delete reference by code
	  * @param code
	  * @return
	  */
	 public void deleteReference(int code) {
		 for (int i = 0; i<this.references.size(); i++) {
			 if(references.get(i).getCode() == code) {
				 this.references.remove(i);
			 }	
		 }
		
	 }
	 
	 public void updateReference(Reference updatedReference) {
		 for (Reference reference : this.references) {
			 if(reference.getCode() == updatedReference.getCode()) {
				 reference = updatedReference;
			 }	
		 }
	 }
	    
	 
	
	/* Reference Controller(End)
     */
     
	 
	 /**
	  * Delete employee by id
	  * @param id
	  * @return
	  */ 
	 public void deleteEmployee(int ID) {
		 for (int i = 0; i<this.employees.size(); i++) {
			 if(employees.get(i).getID() == ID) {
				 this.employees.remove(i);
			 }	
		 }
		
	 }
	 
	 public void updateEmployee(Employee updatedEmployee) {
		 for (Employee employee : this.employees) {
			 if(employee.getID() == updatedEmployee.getID()) {
				 employee = updatedEmployee;
			 }	
		 }
	 }
	 
	 
	/* Employee Controller(End)
     */
		 
}
