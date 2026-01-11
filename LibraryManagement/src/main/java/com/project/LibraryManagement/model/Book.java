package com.project.LibraryManagement.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private String bookCategory;
	private String bookStatus;
	
	@OneToMany(mappedBy="bookId",cascade=CascadeType.REMOVE,orphanRemoval = true)
	private List<UserBooking> bookingId;

}
