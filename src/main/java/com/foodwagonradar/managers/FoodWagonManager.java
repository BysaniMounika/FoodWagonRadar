package com.foodwagonradar.managers;

import java.util.Collection;

import com.foodwagonradar.dao.FoodWagonDao;
import com.foodwagonradar.entities.FoodWagon;

public class FoodWagonManager {
	private static FoodWagonManager instance = new FoodWagonManager();
	private static FoodWagonDao dao = new FoodWagonDao();

	private FoodWagonManager() {
	}

	public static FoodWagonManager getInstance() {
		return instance;
	}

	public Collection<FoodWagon> getFoodWagons(int page, int limit, String serachValue, String sortBy, String userId) {
		return dao.getFoodWagons((page - 1) * limit, limit, serachValue, sortBy, userId);
	}

	public FoodWagon createFoodWagon(long id, String applicant, String days, String startTime, String endTime, Double averageRating) {
		FoodWagon foodWagon = new FoodWagon();
		foodWagon.setId(id);
		foodWagon.setApplicant(applicant);
		foodWagon.setDayOrder(days);
		foodWagon.setStartTime(startTime);
		foodWagon.setEndTime(endTime);
		foodWagon.setAverageRating(averageRating);

		return foodWagon;
	}

	public FoodWagon getFoodWagon(String id) {
		return dao.getFoodWagon(id);
	}

	public FoodWagon createFoodWagon(long id, String applicant, String days, String startTime, String endTime,
			String permit, String permitLocation, String locationDescription, String optionalText, String block,
			String lot, boolean coldTruck, String latitude, String longitude) {
		FoodWagon foodWagon = new FoodWagon();
		foodWagon.setId(id);
		foodWagon.setApplicant(applicant);
		foodWagon.setDayOrder(days);
		foodWagon.setStartTime(startTime);
		foodWagon.setEndTime(endTime);
		foodWagon.setPermit(permit);
		foodWagon.setPermitLocation(permitLocation);
		foodWagon.setLocationDescription(locationDescription);
		foodWagon.setOptionalText(optionalText);
		foodWagon.setBlock(block);
		foodWagon.setLot(lot);
		foodWagon.setColdTruck(coldTruck);
		foodWagon.setLatitude(latitude);
		foodWagon.setLongitude(longitude);

		return foodWagon;
	}

	public int getRecordsCount() {
		return dao.getCount();
	}

	public void addToFavorite(String userId, String foodWagonId) {
		dao.insertFavorite(userId, foodWagonId);
	}

	public boolean isFavorite(String userId, String foodWagonId) {
		return dao.isFavorite(userId, foodWagonId);
	}

}
