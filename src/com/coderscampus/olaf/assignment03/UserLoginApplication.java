package com.coderscampus.olaf.assignment03;

import java.util.Scanner;


public class UserLoginApplication {

    public static void main (String[] args) {

    	final String FILE_NAME = "data.txt";
    	
        UserService userService = new UserService();
		userService.readFile(FILE_NAME);

        Scanner scanner = new Scanner(System.in);
        boolean successfulLogin = false;
        final int MAX_LOGIN_ATTEMPTS = 5;
        
        for (int i = 0; i < MAX_LOGIN_ATTEMPTS; i++) {
            if (i > 0 && i < MAX_LOGIN_ATTEMPTS - 1) {
            	System.out.println("... " + (MAX_LOGIN_ATTEMPTS - i) + " attempts remaining");
            } else if (i == MAX_LOGIN_ATTEMPTS - 1) {
            	System.out.println("last try, bro!");
            }
        	System.out.println("Enter your email:");
            String inputUsername = scanner.nextLine();
            System.out.println("Enter your password:");
            String inputPassword = scanner.nextLine();

            if (userService.validateInputs(inputUsername, inputPassword)) {
                successfulLogin = true;
                break;
            } else {
                System.out.println("\n--------------------------------\n"
                		+ "Invalid login, please try again!"
                		+ "\n--------------------------------\n");
            }
        }

        if (!successfulLogin) {
        	System.out.println("Too many failed login attempts, you are now locked out.");
        }
        scanner.close();
        

    }
}