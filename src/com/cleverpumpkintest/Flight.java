package com.cleverpumpkintest;

import java.util.StringTokenizer;

public class Flight {
	// since we're going to sort
	private int duration;
	
	private String takeoffDate;
	private String takeoffTime;
	private String takeoffTown;
	
	private String landingDate;
	private String landingTime;
	private String landingTown;
	
	private String carrier;
	private String number;
	private String eq;
	//since we're going to sort
	private double price;
	
	private String description;
	private String imageSourcePath;
	
	public int getDuration() {
		return duration;
	}
	public String getDurationAsString() {
		// assuming max duration = 23:59
		return "" + (duration / 60) + ":" + (duration % 60);
	}
	public void setDurationFromString(String duration) {
		StringTokenizer st = new StringTokenizer(duration, ":");
		setDuration(Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken()));
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTakeoffDate() {
		return takeoffDate;
	}
	public void setTakeoffDate(String takeoffDate) {
		this.takeoffDate = takeoffDate;
	}
	public String getTakeoffTime() {
		return takeoffTime;
	}
	public void setTakeoffTime(String takeoffTime) {
		this.takeoffTime = takeoffTime;
	}
	public String getTakeoffTown() {
		return takeoffTown;
	}
	public void setTakeoffTown(String takeoffTown) {
		this.takeoffTown = takeoffTown;
	}
	public String getLandingDate() {
		return landingDate;
	}
	public void setLandingDate(String landingDate) {
		this.landingDate = landingDate;
	}
	public String getLandingTime() {
		return landingTime;
	}
	public void setLandingTime(String landingTime) {
		this.landingTime = landingTime;
	}
	public String getLandingTown() {
		return landingTown;
	}
	public void setLandingTown(String landingTown) {
		this.landingTown = landingTown;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEq() {
		return eq;
	}
	public void setEq(String eq) {
		this.eq = eq;
	}
	public String getPriceAsString() {
		return "" + price;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageSourcePath() {
		return imageSourcePath;
	}
	public void setImageSourcePath(String imageSourcePath) {
		this.imageSourcePath = imageSourcePath;
	}
	
	
}
