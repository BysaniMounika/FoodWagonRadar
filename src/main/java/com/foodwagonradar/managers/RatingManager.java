package com.foodwagonradar.managers;

import java.util.Collection;

import com.foodwagonradar.dao.RatingDao;

public class RatingManager {
	private static RatingManager instance = new RatingManager();
	private static RatingDao dao = new RatingDao();

	private RatingManager() {
	}

	public static RatingManager getInstance() {
		return instance;
	}

	public Collection<Integer> getRating(String foodWagonId) {
		return dao.getRatings(foodWagonId);
	}
	
	public void addRating(String userId, String foodWagonId, String rating) {
		dao.insertRating(userId, foodWagonId, rating);
	}
}
