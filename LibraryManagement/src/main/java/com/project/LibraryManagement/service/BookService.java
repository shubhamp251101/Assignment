package com.project.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import com.project.LibraryManagement.model.Book;
import com.project.LibraryManagement.model.User;

public interface BookService {

	Book addBook(Book book);

	List<Book> getAllBooks();

	Book getBookById(int bookId);

	void deleteBookById(int bookId);

	Book updateBookStatus(int bookId);

	List<Book> getBookByBookCategory(String bookCategory);

	List<Book> getBookByBookStatus(String bookStatus);

	List<Book> getBookByBookAuthor(String bookAuthor);

	List<Book> getBookByBookName(String bookName);
	
	Book setBookStatus(int bookId);

}
