/**
 * 
 */
package com.aws.core;

/**
 * @author jonel.tapang
 *
 */
public class OtherMaterial extends Reference {

	private String creator;
	private String description;
	
	public OtherMaterial(String title, String creator, String description,
			int stock, double replacementAmount, double fines, int borrowDurationLimit) {
		
		super(title, stock, replacementAmount, fines, borrowDurationLimit);
		this.creator = creator;
		this.description = description;
	}

	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getCreator() {
		return this.creator;
	}
	
	
	public String getDescription() {
		return this.description;
	}
	
}
