package com.project.LibraryManagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagement.model.Book;
import com.project.LibraryManagement.model.User;
import com.project.LibraryManagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setRole("ROLE_USER");
		User addedUser = userService.addUser(user);
		return new ResponseEntity<>(addedUser,HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list=userService.getAllUsers();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		User user=userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable int userId) {
		userService.deleteUserById(userId);
		return new ResponseEntity<>("User",HttpStatus.OK);
	}
	
	@PutMapping("/updateUserMemberShip/{userId}/{membershipValidDate}")
	public ResponseEntity<String> updateUserMemberShip(@PathVariable int userId,@PathVariable LocalDate membershipValidDate){
		String membership=userService.updateMembership(userId,membershipValidDate);
		return new ResponseEntity<>(membership,HttpStatus.OK);
	}
	
	@GetMapping("/checkMemership")
	public ResponseEntity<List<User>> checkValidMembership(){
		List<User> validUsers=userService.checkValidMembership();
		return new ResponseEntity<>(validUsers,HttpStatus.OK);
	}

}
