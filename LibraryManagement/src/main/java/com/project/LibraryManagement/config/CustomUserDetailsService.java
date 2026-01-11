package com.project.LibraryManagement.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.LibraryManagement.model.User;
import com.project.LibraryManagement.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail)
            throws UsernameNotFoundException {

        User user = userRepository.findByUserEmail(userEmail)
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + userEmail));

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUserEmail())
            .password(user.getUserPassword())
            .roles(user.getRole().replace("ROLE_", ""))
            .build();
    }
}

