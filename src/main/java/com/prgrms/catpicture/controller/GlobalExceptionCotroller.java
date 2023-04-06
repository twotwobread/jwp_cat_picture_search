package com.prgrms.catpicture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prgrms.catpicture.common.ApiResponse;
import com.prgrms.catpicture.exception.ExceptionMessage;

@RestControllerAdvice
public class GlobalExceptionCotroller {

	@ExceptionHandler(JpaObjectRetrievalFailureException.class)
	public ResponseEntity<ApiResponse> entityNotFoundException(JpaObjectRetrievalFailureException exception) {
		return new ResponseEntity<>(
			ApiResponse.fail(exception.getMessage()),
			HttpStatus.NOT_FOUND
		);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiResponse> nullPointException(NullPointerException exception) {
		return new ResponseEntity<>(
			ApiResponse.fail(ExceptionMessage.CAN_NOT_NULL.getMessage()),
			HttpStatus.NOT_FOUND
		);
	}
}
