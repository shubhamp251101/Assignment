package com.project.LibraryManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.LibraryManagement.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/auth/login","/user/addUser","/books/addBook","/user/getAllUsers").permitAll()
//                    .requestMatchers(HttpMethod.POST, "/books/addBook")
//                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/login")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/user/addUser")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/books/getAllBooks")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/user/getAllUsers")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/book/addNewBook")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/user/updateUserMemberShip/**")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/user/checkMemership")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/booking/categoryPercentage")
                    .hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

