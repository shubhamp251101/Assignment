package com.project.LibraryManagement.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.LibraryManagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByUserEmail(String username);

	List<User> findByMembershipValidDateGreaterThanEqual(LocalDate formattedDate);

}
