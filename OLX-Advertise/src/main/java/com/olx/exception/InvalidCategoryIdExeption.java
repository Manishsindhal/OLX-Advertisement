package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCategoryIdExeption extends RuntimeException {

private String message;
	
	public InvalidCategoryIdExeption() {
		this.message = "";
	}

	public InvalidCategoryIdExeption(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Category Id Exeption [message=" + this.message + "]";
	}
}
