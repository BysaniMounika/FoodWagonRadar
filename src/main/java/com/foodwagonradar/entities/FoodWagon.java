package com.foodwagonradar.entities;

public class FoodWagon {
	private long id;
	private String dayOrder;
	private String startTime;
	private String endTime;
	private String permit;
	private String permitLocation;
	private String locationDescription;
	private String optionalText;
	private String block;
	private String lot;
	private boolean coldTruck;
	private String applicant;
	private String latitude;
	private String longitude;
	private Double averageRating;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDayOrder() {
		return dayOrder;
	}

	public void setDayOrder(String dayOrder) {
		this.dayOrder = dayOrder;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public String getPermitLocation() {
		return permitLocation;
	}

	public void setPermitLocation(String permitLocation) {
		this.permitLocation = permitLocation;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getOptionalText() {
		return optionalText;
	}

	public void setOptionalText(String optionalText) {
		this.optionalText = optionalText;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public boolean isColdTruck() {
		return coldTruck;
	}

	public void setColdTruck(boolean coldTruck) {
		this.coldTruck = coldTruck;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

}
