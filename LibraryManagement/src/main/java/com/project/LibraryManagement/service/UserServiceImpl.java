package com.project.LibraryManagement.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.model.User;
import com.project.LibraryManagement.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
		
	}

	@Override
	public User getUserById(int userId) {
		User user;
		Optional<User> optionalUser=userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			user=optionalUser.get();
		}
		else {
			user = new User();
		}
		return user;
	}

	@Override
	public void deleteUserById(int userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public String updateMembership(int userId, LocalDate membershipValidDate) {
		Optional<User> optionalUser=userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			user.setMembershipValidDate(membershipValidDate);
			userRepository.save(user);
			return "Membership Updated Successful";
		}
		return "Failed to update membership";
	}

	@Override
	public List<User> checkValidMembership() {
		LocalDate today = LocalDate.now();

		return userRepository
		            .findByMembershipValidDateGreaterThanEqual(today);
	}
	
	public boolean checkValidMembership(int userId) {
		LocalDate today = LocalDate.now();

		LocalDate userValidDate=getUserById(userId).getMembershipValidDate();
		return !userValidDate.isBefore(today);
	}

}
