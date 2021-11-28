package com.olx.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAuthTokenExeption extends RuntimeException  {

	private String message;

	public InvalidAuthTokenExeption() {
		this.message = "";
	}

	public InvalidAuthTokenExeption(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Auth Token Exeption [message=" + this.message + "]";
	}
}
