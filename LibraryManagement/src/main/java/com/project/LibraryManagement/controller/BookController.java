package com.project.LibraryManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagement.model.Book;
import com.project.LibraryManagement.service.BookService;
import com.project.LibraryManagement.service.BookingService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookingService bookingService;

	
	@PostMapping("/addNewBook")
	public ResponseEntity<Book> addUser(@RequestBody Book book) {
		System.out.println("Book : "+book.getBookStatus());
		book.setBookStatus("Available");
		System.out.println("Book After : "+book.getBookStatus());
		Book addedBook = bookService.addBook(book);
		return new ResponseEntity<>(addedBook,HttpStatus.OK);
	}
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books=bookService.getAllBooks();
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@GetMapping("/getBook/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int bookId) {
		Book book=bookService.getBookById(bookId);
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	
	@GetMapping("/getBookByCategory/{bookCategory}")
	public ResponseEntity<List<Book>> getBookByBookCategory(@PathVariable String bookCategory) {
		List<Book> books=bookService.getBookByBookCategory(bookCategory);
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@GetMapping("/getBookByStatus/{bookStatus}")
	public ResponseEntity<List<Book>> getBookByBookStatus(@PathVariable String bookStatus) {
		List<Book> books=bookService.getBookByBookStatus(bookStatus);
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@GetMapping("/getBookByName/{bookName}")
	public ResponseEntity<List<Book>> getBookByBookName(@PathVariable String bookName) {
		List<Book> books=bookService.getBookByBookName(bookName);
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@GetMapping("/getBookByAuthor/{bookAuthor}")
	public ResponseEntity<List<Book>> getBookByBookAuthor(@PathVariable String bookAuthor) {
		List<Book> books=bookService.getBookByBookAuthor(bookAuthor);
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable int bookId) {
		bookService.deleteBookById(bookId);
		return new ResponseEntity<>("User",HttpStatus.OK);
	}
	

	@GetMapping("/getCurrentBooks/{userId}")
    public List<Book> getCurrentBooks(@PathVariable int userId) {
        return bookingService.getCurrentBooks(userId);
    }
	
	@GetMapping("/getPreviousBooks/{userId}")
    public List<Book> getPreviousBooks(@PathVariable int userId) {
        return bookingService.getPreviousBooks(userId);
    }
}
