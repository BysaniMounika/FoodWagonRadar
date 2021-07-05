package com.foodwagonradar.managers;

import java.util.Collection;

import com.foodwagonradar.dao.ReviewDao;
import com.foodwagonradar.entities.FoodWagon;
import com.foodwagonradar.entities.Review;
import com.foodwagonradar.entities.User;

public class ReviewManager {
	private static ReviewManager instance = new ReviewManager();
	private static ReviewDao dao = new ReviewDao();

	private ReviewManager() {
	}

	public static ReviewManager getInstance() {
		return instance;
	}

	public Review createReview(String reviewString, User user) {
		Review review = new Review();
		review.setReview(reviewString);
		review.setUser(user);

		return review;
	}

	public Collection<Review> getReviews(String foodWagonId) {
		return dao.getReviews(foodWagonId);
	}
	
	public void addReview(String userId, String foodWagonId, String review) {
		dao.insertReview(userId, foodWagonId, review);
	}
}
