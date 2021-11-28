package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAdvertiseIdExeption extends RuntimeException {
	
	private String message;
	
	public InvalidAdvertiseIdExeption() {
		this.message = "";
	}

	public InvalidAdvertiseIdExeption(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Advertise Id Exeption [message=" + this.message + "]";
	}
	
}
