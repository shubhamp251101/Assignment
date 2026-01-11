package com.project.LibraryManagement.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.LibraryManagement.model.Book;
import com.project.LibraryManagement.model.User;
import com.project.LibraryManagement.model.UserBooking;

public interface BookingRepository extends JpaRepository<UserBooking,Integer>{

	UserBooking findByUserIdAndBookId(User user, Book book);
	
	@Query("SELECT ub.bookId FROM UserBooking ub WHERE ub.userId.userId = :userId AND ub.bookedStatus = true")
    List<Book> findAllBookedBooksByUserId(@Param("userId") int userId);

	@Query("SELECT ub.bookId FROM UserBooking ub WHERE ub.userId.userId = :userId AND ub.bookedStatus = false")
	List<Book> findAllPreviosBooksByUserId(@Param("userId") int userId);

	@Query("SELECT COUNT(ub) FROM UserBooking ub")
    long countAllBookings();

    @Query("""
           SELECT ub.bookId.bookCategory, COUNT(ub)
           FROM UserBooking ub
           GROUP BY ub.bookId.bookCategory
           """)
    List<Object[]> findCategoryCounts();

}
