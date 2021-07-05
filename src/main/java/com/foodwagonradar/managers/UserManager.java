package com.foodwagonradar.managers;

import com.foodwagonradar.dao.UserDao;
import com.foodwagonradar.entities.Review;
import com.foodwagonradar.entities.User;
import com.foodwagonradar.util.StringUtil;

public class UserManager {
	private static UserManager instance = new UserManager();
	private static UserDao dao = new UserDao();
	
	private UserManager() {
	}

	public static UserManager getInstance() {
		return instance;
	}

	public long authenticate(String email, String password) {
		return dao.authenticate(email, StringUtil.encodePassword(password));
	}

	public long addUser(String email, String firstName, String lastName, int gender, String password) {
		return dao.insertUser(email, firstName, lastName, gender, StringUtil.encodePassword(password));
	}
	
	public User createUser(String firstName, String lastName, int gender) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		
		return user;
	}

}
