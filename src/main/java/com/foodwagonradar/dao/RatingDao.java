package com.foodwagonradar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.foodwagonradar.db.ConnectionFactory;

public class RatingDao {
	Connection connection;
	Statement stmt;

	public RatingDao() {
	}

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionFactory.getInstance().getConnection();

		return con;
	}

	public void insertRating(String userId, String foodWagonId, String rating) {
		String queryString = String.format("INSERT INTO rating (user_id, food_wagon_id, rating) VALUES (%s, %s, %s)",
				userId, foodWagonId, rating);
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

	public Collection<Integer> getRatings(String foodWagonId) {
		Collection<Integer> ratings = new ArrayList<>();
		String queryString = String.format("select rating from rating where food_wagon_id = %s", foodWagonId);

		try {
			connection = getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);

			while (rs.next()) {
				int rating = rs.getInt("rating");

				ratings.add(rating);
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

		return ratings;
	}

}
