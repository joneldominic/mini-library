/**
 * 
 */
package com.aws.core;

/**
 * @author jonel.tapang
 *
 */
public class Magazine extends Reference {

	private String editor;
	private int volume;
	
	public Magazine(String title, String editor, int volume, int stock, 
			double replacementAmount, double fines, int borrowDurationLimit) {
		
		super(title, stock, replacementAmount, fines, borrowDurationLimit);
		this.editor = editor;
		this.volume = volume;
	}


	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	
	public String getEditor() {
		return this.editor;
	}
	
	
	public int getVolume() {
		return this.volume;
	}
	
}
