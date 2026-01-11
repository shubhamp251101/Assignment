package com.project.LibraryManagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userContact;
	private LocalDate membershipValidDate;
	private String role;
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.REMOVE,orphanRemoval = true)
	private List<UserBooking> bookings;

}
