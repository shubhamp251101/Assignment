package com.project.LibraryManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.model.Book;
import com.project.LibraryManagement.model.User;
import com.project.LibraryManagement.model.UserBooking;
import com.project.LibraryManagement.repo.BookRepository;
import com.project.LibraryManagement.repo.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;

	@Override
	public String getBook(int userId, int bookId) {
		UserBooking booking=new UserBooking();
		User user=new User();
		Book book=new Book();
		user.setUserId(userId);
		book.setBookId(bookId);
		
		Book checkBook=bookService.getBookById(bookId);
		boolean valid=userService.checkValidMembership(userId);
		
		if(checkBook.getBookStatus().equals("Available") && valid ) {
			System.out.println("Available");
			booking.setUserId(user);
			booking.setBookId(book);
			booking.setBookedStatus(true);
			bookingRepository.save(booking);
			
			bookService.setBookStatus(bookId);
			return "Booking Successful";
		}
		
		
		return "Booking Unsuccessful book is not available or membership expired !";
	}

	@Override
	public String returnBook(int userId, int bookId) {
		UserBooking booking=getBookingByUserBook(userId,bookId);
		bookService.updateBookStatus(bookId);
		booking.setBookedStatus(false);
		bookingRepository.save(booking);
		return "Book Return Successful";
	}
	
	public UserBooking getBookingByUserBook(int userId, int bookId) {
		User user=new User();
		Book book=new Book();
		user.setUserId(userId);
		book.setBookId(bookId);
		return bookingRepository.findByUserIdAndBookId(user, book); 
	}
	
	public List<Book> getCurrentBooks(int userId){
//		User user=new User();
//		user.setUserId(userId);
		return bookingRepository.findAllBookedBooksByUserId(userId);
	}

	@Override
	public List<Book> getPreviousBooks(int userId) {
//		User user=new User();
//		user.setUserId(userId);
		return bookingRepository.findAllPreviosBooksByUserId(userId);
	}
	
	
	public Map<String, Double> getCategoryPercentageAll() {

        long totalBookings = bookingRepository.countAllBookings();
        if (totalBookings == 0) {
            return new HashMap<>();
        }

        List<Object[]> categoryCounts = bookingRepository.findCategoryCounts();
        Map<String, Double> result = new HashMap<>();

        for (Object[] row : categoryCounts) {
            String category = (String) row[0];
            long count = (Long) row[1];

            double percentage = (count * 100.0) / totalBookings;
            result.put(category, percentage);
        }

        return result;
    }

}
