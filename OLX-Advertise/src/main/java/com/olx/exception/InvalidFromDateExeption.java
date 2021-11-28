package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidFromDateExeption extends RuntimeException  {

	private String message;

	public InvalidFromDateExeption() {
		this.message = "";
	}

	public InvalidFromDateExeption(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid From Date Exeption [message=" + this.message + "]";
	}
}
