package com.netflix.movieService.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = MovieNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleDepartmentNotFound(MovieNotFoundException ex) {

		ErrorMessage message = new ErrorMessage(ex.getMessage(), new Date(), HttpStatus.NOT_FOUND.name());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);

	}

}