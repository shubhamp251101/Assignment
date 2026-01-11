package com.project.LibraryManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.LibraryManagement.model.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{

	List<Book> findByBookCategory(String bookCategory);

	List<Book> findByBookStatus(String bookStatus);

	List<Book> findByBookAuthor(String bookAuthor);

	List<Book> findByBookName(String bookName);

}
