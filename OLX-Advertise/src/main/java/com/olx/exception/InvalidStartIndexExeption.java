package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidStartIndexExeption extends RuntimeException  {

	private String message;

	public InvalidStartIndexExeption() {
		this.message = "";
	}

	public InvalidStartIndexExeption(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Start Index Exeption [message=" + this.message + "]";
	}
}
