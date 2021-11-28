package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidStatusIdExeption extends RuntimeException {

private String message;
	
	public InvalidStatusIdExeption() {
		this.message = "";
	}

	public InvalidStatusIdExeption(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Status Id Exeption [message=" + this.message + "]";
	}
}
