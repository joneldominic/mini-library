/**
 * 
 */
package com.aws.core;

/**
 * @author jonel.tapang
 *
 */
public class VideoCD extends Reference {

	private String director;
	private double duration;
	
	public VideoCD(String title, String director, double duration, int stock, 
			double replacementAmount, double fines, int borrowDurationLimit) {
		
		super(title, stock, replacementAmount, fines, borrowDurationLimit);
		this.director = director;
		this.duration = duration;
	}
	

	public void setDirector(String director) {
		this.director = director;
	}
	
	
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	
	public String getDirector() {
		return this.director;
	}
	
	
	public double getDuration() {
		return this.duration;
	}
	

}
