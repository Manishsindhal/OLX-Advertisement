package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRecordNoExeption extends RuntimeException {
	
	private String message;

	public InvalidRecordNoExeption() {
		this.message = "";
	}

	public InvalidRecordNoExeption(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "No Record Found Exeption [message=" + this.message + "]";
	}
}
