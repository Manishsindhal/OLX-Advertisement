package com.olx.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


import com.olx.dto.Users;

public interface UserService extends UserDetailsService {

	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authToken);
	public ResponseEntity<String> authenticate(@RequestBody Users users);
	public Users registerUser(@RequestBody Users users);
	public boolean logoutUser(@RequestHeader("auth-token") String authToken);
	public Users getUserDetails(@RequestHeader("auth-token") String authToken);
}
