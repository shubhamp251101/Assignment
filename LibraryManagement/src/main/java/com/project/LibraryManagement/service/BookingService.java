package com.project.LibraryManagement.service;

import java.util.List;
import java.util.Map;

import com.project.LibraryManagement.model.Book;
import com.project.LibraryManagement.model.UserBooking;

public interface BookingService{

	String getBook(int userId, int bookId);

	String returnBook(int userId, int bookId);
	
	List<Book> getCurrentBooks(int userId);

	List<Book> getPreviousBooks(int userId);

	Map<String, Double> getCategoryPercentageAll();

}
