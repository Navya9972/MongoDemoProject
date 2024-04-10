package com.Mongodb.MongoDb.payload;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class ErrorResponse {

	private String message;
	private HttpStatus httpStatus;
	private boolean success;	
}
