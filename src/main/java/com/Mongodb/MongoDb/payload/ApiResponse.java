package com.Mongodb.MongoDb.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApiResponse {
	private boolean success;
	private String message;
	private Object responseData;
	
	public ApiResponse(boolean success, String message, boolean successs) {
		super();
		this.success = success;
		this.message = message;
		this.success = successs;
	}
	
	
	
	
	
}


