package com.foodwagonradar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.foodwagonradar.db.ConnectionFactory;
import com.foodwagonradar.entities.Review;
import com.foodwagonradar.entities.User;
import com.foodwagonradar.managers.ReviewManager;
import com.foodwagonradar.managers.UserManager;

public class ReviewDao {
	Connection connection;
	Statement stmt;

	public ReviewDao() {
	}

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionFactory.getInstance().getConnection();

		return con;
	}

	public void insertReview(String userId, String foodWagonId, String review) {
		String queryString = String.format("INSERT INTO review (user_id, food_wagon_id, review) VALUES (%s, %s, '%s')",
				userId, foodWagonId, review);
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(queryString);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Collection<Review> getReviews(String foodWagonId) {
		Collection<Review> reviews = new ArrayList<>();
		String queryString = String.format("select review, first_name, last_name, gender_id from review join user on review.user_id = user.id where food_wagon_id = %s", foodWagonId);

		try {
			connection = getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);

			while (rs.next()) {
				String reviewString = rs.getString("review");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int gender = rs.getInt("gender_id");
						
				User user = UserManager.getInstance().createUser(firstName, lastName, gender);
				Review review = ReviewManager.getInstance().createReview(reviewString, user);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reviews;
	}

}
