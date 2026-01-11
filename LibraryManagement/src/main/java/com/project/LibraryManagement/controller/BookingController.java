package com.project.LibraryManagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagement.model.UserBooking;
import com.project.LibraryManagement.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/getBook/{userId}/{bookId}")
	public ResponseEntity<String> getBook(@PathVariable int userId,@PathVariable int bookId) {
		String booking = bookingService.getBook(userId,bookId);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	
	@PutMapping("/returnBook/{userId}/{bookId}")
	public ResponseEntity<String> returnBook(@PathVariable int userId,@PathVariable int bookId){
		String booking = bookingService.returnBook(userId,bookId);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}

	 @GetMapping("/categoryPercentage")
	    public Map<String, Double> getCategoryPercentage() {
	        return bookingService.getCategoryPercentageAll();
	    }
}
