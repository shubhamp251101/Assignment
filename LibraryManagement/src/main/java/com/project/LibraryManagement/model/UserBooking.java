package com.project.LibraryManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class UserBooking {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookingId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "bookId")
	@JsonIgnore
	private Book bookId;
	
	private boolean bookedStatus;
	
}
