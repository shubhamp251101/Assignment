package com.project.LibraryManagement.controller;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.LibraryManagement.config.CustomUserDetailsService;
import com.project.LibraryManagement.model.User;
import com.project.LibraryManagement.security.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager am, CustomUserDetailsService uds, JwtService js) {
        this.authenticationManager = am;
        this.userDetailsService = uds;
        this.jwtService = js;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
    	System.out.println("in login");
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserEmail());
        String token = jwtService.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }
}

