package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value= {InvalidAdvertiseIdExeption.class})
	public ResponseEntity<Object> handleInvalidAdvertiseIdError(RuntimeException exception,
			WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value= {InvalidStatusIdExeption.class})
	public ResponseEntity<Object> handleInvalidStatusIdError(RuntimeException exception,
			WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value= {InvalidCategoryIdExeption.class})
	public ResponseEntity<Object> handleInvalidCategoryIdError(RuntimeException exception,
			WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value= {InvalidAuthTokenExeption.class})
	public ResponseEntity<Object> handleInvalidAuthTokenError(RuntimeException exception,
			WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value= {InvalidFromDateExeption.class})
	public ResponseEntity<Object> handleInvaliFromDateError(RuntimeException exception,
			WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value= {InvalidOnDateExeption.class})
	public ResponseEntity<Object> handleInvaliOnDateError(RuntimeException exception,
			WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value= {InvalidRecordNoExeption.class})
	public ResponseEntity<Object> handleInvaliRecordNoError(RuntimeException exception,
			WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value= {InvalidStartIndexExeption.class})
	public ResponseEntity<Object> handleInvaliStartIndexError(RuntimeException exception,
			WebRequest request) {
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
}
