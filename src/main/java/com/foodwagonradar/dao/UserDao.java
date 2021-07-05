package com.foodwagonradar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.foodwagonradar.db.ConnectionFactory;

public class UserDao {
	Connection connection;
	Statement stmt;

	public UserDao() {
	}

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionFactory.getInstance().getConnection();

		return con;
	}

	public long authenticate(String email, String password) {
		String queryString = "Select id from user where email = '" + email + "' and password = '" + password + "'";

		try {
			connection = getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);

			if (rs.next()) {
				return rs.getLong("id");
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

		return -1;
	}

	public long insertUser(String email, String firstName, String lastName, int gender, String encodePassword) {
		String[] returnId = { "id" };
		String queryString = String.format(
				"INSERT INTO user (email, password, first_name, last_name, gender_id) VALUES ('%s', '%s', '%s', '%s', %d)",
				email, encodePassword, firstName, lastName, gender);

		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(queryString, returnId);
			statement.executeUpdate();

			try (ResultSet rs = statement.getGeneratedKeys()) {
			    if (rs.next()) {
			        return rs.getLong(1);
			    }
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

		return -1;
	}

}
