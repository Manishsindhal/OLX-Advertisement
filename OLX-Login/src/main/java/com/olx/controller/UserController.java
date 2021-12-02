package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Users;
import com.olx.security.jwtUtil;
import com.olx.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/user")
public class UserController {

	//@Autowired
	//AuthenticationManager authenticationManager;

	@Autowired
	jwtUtil jwtUtil;

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserService userService;

	@GetMapping(value = "/user/validate/token")
	@ApiOperation(value = "Validate token and return boolean value corrsponding")
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authToken) {
		return userService.validateToken(authToken);
	}

	@PostMapping(value = "/user/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "User authenticatation and return token")
	public ResponseEntity<String> authenticate(@RequestBody Users user) {
		return userService.authenticate(user);
	}
	
	@PostMapping(value = "/user", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "User Registration")
	public Users registerUser(@RequestBody Users user) {
		return userService.registerUser(user);
	}
	
	@DeleteMapping(value = "/user/logout")
	@ApiOperation(value = "Logout User")
	public boolean logoutUser(@RequestHeader("auth-token") String authToken) {
		return userService.logoutUser(authToken);
	}
	
	@GetMapping(value = "/user", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Get User infrmation")
	public Users getUserDetails(@RequestHeader("auth-token") String authToken) {
		return userService.getUserDetails(authToken);
	}
	
}
