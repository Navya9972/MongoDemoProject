package com.Mongodb.MongoDb.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Mongodb.MongoDb.customexception.EmployeeIdNotFound;
import com.Mongodb.MongoDb.customexception.EmployeeNotFound;
import com.Mongodb.MongoDb.payload.ErrorResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> Exception(Exception e) {
		String message = "An error occured !! " + e.getMessage();
		ErrorResponse response = ErrorResponse.builder().message(message).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.success(false).build();
		return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(EmployeeIdNotFound.class)
	public ResponseEntity<ErrorResponse> EmployeeIdNotFoundException(EmployeeIdNotFound e) {
		String message = e.getMessage();
		ErrorResponse response = ErrorResponse.builder().message(message).httpStatus(HttpStatus.NOT_FOUND)
				.success(true).build();
		return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeNotFound.class)
	public ResponseEntity<ErrorResponse> EmployeeNotFoundException(EmployeeNotFound e) {
		String message = e.getMessage();
		ErrorResponse response = ErrorResponse.builder().message(message).httpStatus(HttpStatus.NOT_FOUND)
				.success(true).build();
		return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
	}
	
}
