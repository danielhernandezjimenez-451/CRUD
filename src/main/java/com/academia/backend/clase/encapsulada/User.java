package com.academia.backend.clase.encapsulada;
import java.util.HashSet;

public class User {
	
	public static HashSet<String> emailsInUse = new HashSet<String>();
	public static HashSet<String> usernamesInUse = new HashSet<String>();
	
	public static User createNewUser(String username, String email, String password) {
		if (validateUniqueUsername(username) & validateUniqueEmail(email) & validatePasswordCriteria(password)) {
			System.out.println("User created successfuly");
			return new User(username, email, password);
		}
		return null;
	}
	
	public static boolean validateUniqueEmail(String email) {
		if (emailsInUse.contains(email)) {
			System.out.println("The email: "+ email + " is already in use");
			return false;
		}
		return true;
	}
	
	public static boolean validateUniqueUsername(String username) {
		if (usernamesInUse.contains(username)) {
			System.out.println("The username: " + username + " is already in use");
			return false;
		}
		return true;
	}
	
	public static boolean validatePasswordCriteria(String password) {
		boolean passwordIsValid = false;
		if(password.length() >= 8) {
			return true;
		}
		System.out.println("Password must be at least 8 characters");
		return passwordIsValid;
	}
	
	public String username;
	public String email;
	private String password;
	private HashSet<String> previousPasswords;
	
	private User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		previousPasswords = new HashSet<String>();
		previousPasswords.add(password);
		emailsInUse.add(email);
		usernamesInUse.add(username);
	}
	
	private void setEmail(String email) {
		this.email = email;
	}
	
	private void setUsername(String username) {
		this.username = username;
	}
	
	private void setPassword(String password) {
		previousPasswords.add(this.password);
		this.password = password;
	}
	
	public void changeEmail(String newEmail) {
		if(validateUniqueEmail(newEmail)) {
			setEmail(newEmail);
			System.out.println("The email has been changed successfully");
		}else {
			System.out.println("The new email is already in use");
		}
	}
	
	public void changeUsername(String newUsername) {
		if(validateUniqueUsername(newUsername)) {
			setUsername(newUsername);
		}
	}
	
	public void changePassword(String newPassword) {
		if(validateNewPassword(newPassword) & validatePasswordCriteria(newPassword)) {
			setPassword(newPassword);
			System.out.println("The password has been changed successfully");
		}else {
			System.out.println("The password didn't meet the requirements");
		}

	}
	
	private boolean validateNewPassword(String password) {
			if(previousPasswords.contains(password)) {
				System.out.println("Your new password cannot be the same as your previous passwords");
				return false;
			}
		return true;
	}
	
	public void logIn(String usernameOrEmail, String password) {
		if((usernameOrEmail.equals(username) || usernameOrEmail.equals(email) && this.password.equals(password))) {
			System.out.println("You have successfully logged in!!");
		}else {
			System.out.println("The credentials you entered don't match any user");
		}
	}
	
	@Override
	public String toString() {
		return "Email: " + email + " Username: " + username;
	}
	
}
