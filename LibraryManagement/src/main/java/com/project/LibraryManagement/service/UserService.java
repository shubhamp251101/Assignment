package com.project.LibraryManagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.project.LibraryManagement.model.User;

public interface UserService {

	List<User> getAllUsers();

	User addUser(User user);

	User getUserById(int userId);

	void deleteUserById(int userId);

	String updateMembership(int userId, LocalDate membershipValidDate);

	List<User> checkValidMembership();
	
	boolean checkValidMembership(int userId);
	
}
