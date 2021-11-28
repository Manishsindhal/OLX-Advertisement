package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidOnDateExeption extends RuntimeException  {

	private String message;

	public InvalidOnDateExeption() {
		this.message = "";
	}

	public InvalidOnDateExeption(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid On Date Exeption [message=" + this.message + "]";
	}
}
