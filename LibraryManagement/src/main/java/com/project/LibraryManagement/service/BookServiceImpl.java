package com.project.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.model.Book;
import com.project.LibraryManagement.repo.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(int bookId) {
		Book book;
		Optional<Book> optionalBook=bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			book=optionalBook.get();
		}
		else {
			book=new Book();
		}
		return book;
	}

	@Override
	public void deleteBookById(int bookId) {
		bookRepository.deleteById(bookId);
	}
	
	public Book updateBookStatus(int bookId) {
		Book book;
		Optional<Book> optionalBook=bookRepository.findById(bookId);
		
		if(optionalBook.isPresent()) {
			book=optionalBook.get();
			book.setBookStatus("Available");
			bookRepository.save(book);
		}
		else {
			book=new Book();
		}
		return book;
	}

	@Override
	public List<Book> getBookByBookCategory(String bookCategory) {
		
		return bookRepository.findByBookCategory(bookCategory);
	}

	@Override
	public List<Book> getBookByBookStatus(String bookStatus) {
		return bookRepository.findByBookStatus(bookStatus);
	}

	@Override
	public List<Book> getBookByBookAuthor(String bookAuthor) {
		return bookRepository.findByBookAuthor(bookAuthor);
	}

	@Override
	public List<Book> getBookByBookName(String bookName) {
		return bookRepository.findByBookName(bookName);
	}
	
	public Book setBookStatus(int bookId) {
		Book book=getBookById(bookId);
		book.setBookStatus("Taken");
		return bookRepository.save(book);
	}

}
