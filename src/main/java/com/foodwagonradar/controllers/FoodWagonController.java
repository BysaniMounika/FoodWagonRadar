package com.foodwagonradar.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodwagonradar.entities.Review;
import com.foodwagonradar.managers.FoodWagonManager;
import com.foodwagonradar.managers.RatingManager;
import com.foodwagonradar.managers.ReviewManager;

/**
 * Servlet implementation class FoodWagon
 */
@WebServlet(urlPatterns = {"/food-wagon", "/food-wagon/add-to-favorite", "/food-wagon/rating", "/food-wagon/review"})
public class FoodWagonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodWagonController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodWagonId = request.getParameter("id");
		String userId = request.getSession().getAttribute("userId") == null ? null : String.valueOf(request.getSession().getAttribute("userId"));
		if (request.getServletPath().contains("add-to-favorite")) {
			FoodWagonManager.getInstance().addToFavorite(userId, foodWagonId);
		}

		com.foodwagonradar.entities.FoodWagon foodWagon = FoodWagonManager.getInstance().getFoodWagon(request.getParameter("id"));
		request.setAttribute("foodWagon", foodWagon);
		request.setAttribute("isFavorite", false);
		if (userId != null) {
			boolean isFavorite = FoodWagonManager.getInstance().isFavorite(userId, foodWagonId);
			request.setAttribute("isFavorite", isFavorite);
		}
		
		Collection<Review> reviews = ReviewManager.getInstance().getReviews(foodWagonId);
		request.setAttribute("totalReviews", reviews.size());
		request.setAttribute("reviews", reviews);
		
		Collection<Integer> ratings = RatingManager.getInstance().getRating(foodWagonId);
		int[] ratingCounts = new int[5];
		int totalRatings = 0;
		for (Integer rating: ratings) {
			ratingCounts[rating - 1] ++;
			totalRatings ++;
		}
		request.setAttribute("ratingCounts", ratingCounts);
		request.setAttribute("totalRatings", totalRatings);
		
		request.getRequestDispatcher("/foodWagon.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodWagonId = request.getParameter("id");
		String userId = request.getSession().getAttribute("userId") == null ? null : String.valueOf(request.getSession().getAttribute("userId"));
		if (request.getServletPath().contains("rating")) {
			String rating = request.getParameter("rating");
			RatingManager.getInstance().addRating(userId, foodWagonId, rating);
		} else if (request.getServletPath().contains("review")) {
			String review = request.getParameter("review");
			ReviewManager.getInstance().addReview(userId, foodWagonId, review);
		}
		
		response.sendRedirect("?id=" + foodWagonId);
	}

}
