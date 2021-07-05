package com.foodwagonradar.util;

import java.security.MessageDigest;

public class StringUtil {
	private final static String ENC_ALGO = "SHA";
	public final static String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	public static String encodePassword(String password) {
		byte[] unencodedPassword = password.getBytes();
		MessageDigest md = null;
		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(ENC_ALGO);
		} catch (Exception e) {
			// log.error("Exception: " + e);
			return password;
		}
		md.reset();
		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);
		// now calculate the hash
		byte[] encodedPassword = md.digest();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}
}
