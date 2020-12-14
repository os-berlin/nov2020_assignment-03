package com.coderscampus.olaf.assignment03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserService {

	final int USER_ARRAY_SIZE = 4;

	User[] users = new User[USER_ARRAY_SIZE];
	boolean correctUsername = false;
	boolean correctPassword = false;

	public void readFile(String fileName) {

		BufferedReader fileReader = null;

		try {
			fileReader = new BufferedReader(new FileReader(fileName));

			int i = 0;
			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] userDetails = line.split(",");
				users[i] = createNewUser(userDetails);
				i++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found!\n\n..terminating");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("I/O error!\n\n..terminating");
			System.exit(0);
		} catch (IndexOutOfBoundsException e) {
			// in case data.txt is more than (USER_ARRAY_SIZE) lines
			System.out.println("Too many users to import! Please check file" + "\n\n..terminating");
			System.exit(0);
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				System.out.println("I/O error!\n\n..terminating");
				System.exit(0);
			}
		}
	}

	public User createNewUser(String[] userDetails) {
		User user = new User();
		user.setUsername(userDetails[0]);
		user.setPassword(userDetails[1]);
		user.setName(userDetails[2]);

		return user;
	}

	public boolean validateInputs(String username, String password) {

		for (int i = 0; i < users.length; i++) {
			correctUsername = username.equalsIgnoreCase(users[i].getUsername());
			correctPassword = password.equals(users[i].getPassword());
			if (correctUsername && correctPassword) {
				System.out.println("\n-------\n" + "Welcome: " + users[i].getName());
				return true;
			}
		}
		return false;
	}
}