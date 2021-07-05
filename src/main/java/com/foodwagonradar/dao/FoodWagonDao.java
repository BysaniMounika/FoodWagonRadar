package com.foodwagonradar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.foodwagonradar.db.ConnectionFactory;
import com.foodwagonradar.entities.FoodWagon;
import com.foodwagonradar.managers.FoodWagonManager;
import com.foodwagonradar.util.StringUtil;

public class FoodWagonDao {
	Connection connection;
	Statement stmt;
	private int count;
	
	public FoodWagonDao() {
	}

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionFactory.getInstance().getConnection();

		return con;
	}

	public Collection<FoodWagon> getFoodWagons(int offset, int rowCount, String serachValue, String sortBy, String userId) {
		Collection<FoodWagon> result = new ArrayList<>();
		String queryString = "select SQL_CALC_FOUND_ROWS food_wagon.*, avg(rating) as average_rating from food_wagon";
		
		if (userId != null) {
			queryString += " join favorite on favorite.food_wagon_id = food_wagon.id and favorite.user_id = " + userId;
		}
		
		queryString += " left join rating on rating.food_wagon_id = food_wagon.id";
		
		if (serachValue != null) {
			queryString += " where food_wagon.applicant like '%" + serachValue + "%'";
		}
		
		queryString += " group by food_wagon.id";
		
		if (sortBy != null) {
			queryString += " order by " + sortBy + " ASC";
		}
		
		queryString += " limit " + offset + ", " + rowCount;
		System.out.printf("\nQueryString: %s\n", queryString);
		
		try {
			connection = getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);

			while (rs.next()) {
				long id = rs.getLong("id");
				String applicant = rs.getString("applicant");
				int dayOrder = rs.getInt("day_order");
				String startTime = rs.getString("start_time_24");
				String endTime = rs.getString("end_time_24");
				Double averageRating = rs.getDouble("average_rating");

				FoodWagon foodWagon = FoodWagonManager.getInstance().createFoodWagon(id, applicant, StringUtil.days[dayOrder], startTime,
						endTime, averageRating);
				result.add(foodWagon);
			}
			
			queryString = "SELECT FOUND_ROWS() as count";
			rs = stmt.executeQuery(queryString);
			if(rs.next()) {
				count = rs.getInt("count");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int getCount() {
//		int count = 0;
//		String queryString = "SELECT FOUND_ROWS() as count";
//		
//		try {
//			connection = getConnection();
//			stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery(queryString);
//			
//			if(rs.next()) {
//				count = rs.getInt("count");
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		return count;
	}

	public FoodWagon getFoodWagon(String foodWagonId) {
		FoodWagon foodWagon = new FoodWagon();
		String queryString = "select * from food_wagon where id = " + foodWagonId;
		
		try {
			Connection connection = getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);

			while (rs.next()) {
				long id = rs.getLong("id");
				String applicant = rs.getString("applicant");
				int dayOrder = rs.getInt("day_order");
				String startTime = rs.getString("start_time_24");
				String endTime = rs.getString("end_time_24");
				String permit = rs.getString("permit");
				String permitLocation = rs.getString("permit_location");
				String locationDescription = rs.getString("location_description");
				String optionalText = rs.getString("optional_text");
				String block = rs.getString("block");
				String lot = rs.getString("lot");
				boolean coldTruck = rs.getString("cold_truck") == "Y" ? true : false;
				String latitude = rs.getString("latitude");
				String longitude = rs.getString("longitude");

				foodWagon = FoodWagonManager.getInstance().createFoodWagon(id, applicant, StringUtil.days[dayOrder], startTime, endTime,
						permit, permitLocation, locationDescription, optionalText, block, lot, coldTruck, latitude,
						longitude);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return foodWagon;
	}

	public void insertFavorite(String userId, String foodWagonId) {
		String queryString = String.format(
				"INSERT INTO favorite (user_id, food_wagon_id) VALUES (%s, %s)",
				userId, foodWagonId);

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
	
	public boolean isFavorite(String userId, String foodWagonId) {
		String queryString = String.format(
				"select * from favorite where user_id = %s and food_wagon_id = %s",
				userId, foodWagonId);

		try {
			connection = getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			
			if(rs.next()) {
				return true;
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
		
		return false;
	}
}
